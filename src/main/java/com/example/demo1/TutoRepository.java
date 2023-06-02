package com.example.demo1;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutoRepository extends JpaRepository<Tuto,Long>
{
	public List<Tuto> findByTitleContaining(String title);
}