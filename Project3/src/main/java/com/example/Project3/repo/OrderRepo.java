package com.example.Project3.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Project3.entity.Orders;

public interface OrderRepo extends JpaRepository<Orders, Integer>{
//	@Query("SELECT o FROM Orders o WHERE o.createdAt >= :x")
//	List<Orders> searchByDate(@Param("x") Date s);

	/// Đếm số lượng đơn group by MONTH(buyDate)
	// - dùng custom object để build
	// SELECT id, MONTH(buyDate) from bill;
	// select count(*), MONTH(buyDate) from bill
	// group by MONTH(buyDate)
	@Query("SELECT count(b.id), month(b.createdAt), year(b.createdAt) "
			+ "FROM Orders b GROUP BY month(b.createdAt), year(b.createdAt) ")
	List<Object[]> thongKeBOrder();

//	@Query("SELECT new jmaster.io.project3.dto.BillStatisticDTO(count(b.id), '/') "
//			+ " FROM Order b GROUP BY month(b.createdAt), year(b.createdAt) ")
//	List<BillStatisticDTO> thongKeBill2();
}
