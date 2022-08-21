package com.desafio.cooperativismo.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cooperativismo.dtos.TopicDTO;
import com.desafio.cooperativismo.enums.ErrorMessageEnum;
import com.desafio.cooperativismo.exceptions.MissingParameterException;
import com.desafio.cooperativismo.models.Topic;
import com.desafio.cooperativismo.repositories.TopicRepository;

@Service
public class TopicService {

  @Autowired
  TopicRepository topicRepository;

  /**
   * Método que retorna todas as pautas (Topic)
   * 
   * @return List<Topic> lista de pautas (Topic)
   */
  public List<Topic> getTopics() {
    return topicRepository.findAll();
  }

  /**
   * Método para realizar a criação da pauta (Topic)
   * 
   * @param topicDTO DTO (Data Transfer Object) da pauta (Topic).
   * @throws MissingParameterException caso algum parâmetro obrigatório não seja
   *                                   enviado pelo DTO
   */
  public void createTopic(TopicDTO topicDTO) {
    var topic = new Topic();
    BeanUtils.copyProperties(topicDTO, topic);
    requiredNameValidation(topic);
    topicRepository.save(topic);
  }

  private void requiredNameValidation(Topic topic) {
    if (topic.getName() == null || topic.getName().equals("")) {
      throw new MissingParameterException(ErrorMessageEnum.REQUIRED_NAME_FIELD);
    }
  }
}
