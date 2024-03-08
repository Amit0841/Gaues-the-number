package com.masai.service;

import java.util.List;
import java.util.Map;

import com.masai.model.Responce;

public interface ServiceInterface {

	Responce createGame(Integer level, String name);

	Responce testNumber(Integer number);

	List<Responce> getResult();

	Responce resetGame();

	Responce sms();

}
