package com.desafio.cooperativismo.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.desafio.cooperativismo.responses.CPFResponse;

@FeignClient(url = "${feign.cpf.url}", name = "cpf")
public interface CPFClient {

  @GetMapping("/{cpf}")
  CPFResponse getStatus(@PathVariable String cpf);
}
