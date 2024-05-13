package com.renatobonfim.projectmanagerbackend.repository;

import com.renatobonfim.projectmanagerbackend.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, String> {
}
