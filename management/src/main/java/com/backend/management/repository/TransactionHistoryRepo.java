package com.backend.management.repository;

import com.backend.management.model.Member;
import com.backend.management.model.TransactionHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionHistoryRepo extends MongoRepository<TransactionHistory, String> {
    Member findByPhoneNumber(String phoneNumber);
    // Tìm tất cả các giao dịch của một thành viên cụ thể
    List<TransactionHistory> findByMemberId(String memberId);

    // Tìm tất cả các giao dịch theo loại giao dịch (mượn, trả, gia hạn, quá hạn)
    List<TransactionHistory> findByTransactionType(String transactionType);

    // dem so sach dang o trang thai muon
    List<TransactionHistory> findByTransactionTypeAndStatus(String transactionType, boolean status) ;

    // Tìm tất cả các giao dịch của một cuốn sách cụ thể
    List<TransactionHistory> findByBookId(String bookId);


    List<TransactionHistory> findByMemberIdAndBookIdAndTransactionTypeAndStatus(
            String memberId, String bookId, String transactionType, boolean status);

    List<TransactionHistory> findByMemberIdAndStatusAndDueDateBefore(String memberId, boolean status, LocalDateTime dueDate);


    List<TransactionHistory> findByPhoneNumberAndStatusAndDueDateBefore(String phoneNumber, boolean status, LocalDateTime dueDate);

    long countByTransactionTypeAndStatus(String transactionType, boolean status);

    List<TransactionHistory> findByMemberIdAndStatus(String memberId, boolean status);

    // Thêm vào TransactionHistoryRepo
    long countByTransactionTypeAndTransactionDateBetween(
            String transactionType,
            LocalDateTime startDate,
            LocalDateTime endDate
    );

    List<TransactionHistory> findByStatus(String status);
}
