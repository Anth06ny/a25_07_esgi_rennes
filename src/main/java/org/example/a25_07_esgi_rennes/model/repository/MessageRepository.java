package org.example.a25_07_esgi_rennes.model.repository;

import org.example.a25_07_esgi_rennes.model.MessageBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageBean, Long> {

    List<MessageBean> findTop10ByOrderByIdDesc();

}
