package com.desafio.springboot.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.desafio.springboot.responses.CPFResponse;

@FeignClient(url = "${feign.cpf.url}", name = "cpf")
public interface CPFClient {

  @GetMapping("/{cpf}")
  CPFResponse getStatus(@PathVariable String cpf);
}
