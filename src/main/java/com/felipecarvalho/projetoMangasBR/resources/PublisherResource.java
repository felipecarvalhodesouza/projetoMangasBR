package com.felipecarvalho.projetoMangasBR.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.felipecarvalho.projetoMangasBR.domain.Publisher;

@RestController
@RequestMapping(value="/publishers")
public class PublisherResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Publisher> list(){
		
		Publisher p1 = new Publisher(1, "Panini");
		Publisher p2 = new Publisher(2, "JBC");
		Publisher p3 = new Publisher(3, "New Pop");
		
		List<Publisher> list = new ArrayList<>();
		list.addAll(Arrays.asList(p1,p2,p3));
		
		return list;
	}

}
