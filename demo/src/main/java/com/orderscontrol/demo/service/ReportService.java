package com.orderscontrol.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.orderscontrol.demo.dto.CommissionReportDto;
import com.orderscontrol.demo.dto.CommissionReportDto.ListDescription;
import com.orderscontrol.demo.entity.Order;
import com.orderscontrol.demo.entity.OrderDetail;
import com.orderscontrol.demo.entity.User;
import com.orderscontrol.demo.repository.OrderRepository;
import com.orderscontrol.demo.repository.UserRepository;
import com.sipios.springsearch.anotation.SearchSpec;

/**
 * @author jmpiedra
 */
@Service
public class ReportService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public List<CommissionReportDto> commissionList(Specification<Order> specs) {

		List<Order> orderList = repository.findAll(Specification.where(specs));

		List<User> users = userRepository.findAll();
		List<CommissionReportDto> reportList = new ArrayList<CommissionReportDto>();

		for (User user : users) {
			CommissionReportDto report = commissionByUser(orderList, user.getUsername(), null, null);
			if (report.getItems() != null && report.getItems().size() > 0) {
				reportList.add(report);
			}
		}
		return reportList;
	}

	@Transactional
	public CommissionReportDto commissionByUser(List<Order> orderList, String username, String date, String status) {

		CommissionReportDto report = new CommissionReportDto();
		report.setUsername(username);
		double totalCommission = 0;
		List<ListDescription> itemsReport = new ArrayList<CommissionReportDto.ListDescription>();
		for (Order order : orderList) {

			for (OrderDetail detail : order.getOrderDetails()) {

				boolean contains = Arrays.stream(detail.getParticipants()).anyMatch(username::equals);
				if (contains) {
					ListDescription itemReport = new ListDescription();
					itemReport.setOrderId(order.getId());
					itemReport.setOrderTotal(order.getTotal());
					itemReport.setOrderClient(order.getClientName());
					itemReport.setOrderDate(order.getCreationDate().toString());
					itemReport.setOrderStatus(order.getStatus());

					double commission;
					if (detail.getParticipants().length > 1) {
						commission = detail.getItem().getCommissionPrice2();
					} else {
						commission = detail.getItem().getCommissionPrice1();
					}
					totalCommission += commission;
					itemReport.setItemId(detail.getItem().getId());
					itemReport.setDescription(detail.getDescription());
					itemReport.setCommission(commission);
					itemReport.setItemsTotal(detail.getPrice());
					itemsReport.add(itemReport);
				}

			}

		}
		report.setTotal(totalCommission);
		report.setItems(itemsReport);

		return report;
	}

}
