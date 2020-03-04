package com.igeekhome.egobuycontentservice.controller;

import com.igeekhome.egobuy.content.pojo.TbContent;
import com.igeekhome.egobuy.util.CustomPageInfo;
import com.igeekhome.egobuy.util.ResponseEntity;
import com.igeekhome.egobuycontentservice.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class ContentController {

	@Autowired
	TbContentService tbContentService;

	@RequestMapping("/list/{id}")
	public ResponseEntity list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit, @PathVariable Long id) {
		CustomPageInfo<TbContent> tbContentCustomPageInfo = tbContentService.queryByParentId(id, page, limit);
		System.out.println(tbContentCustomPageInfo.getData());
		return ResponseEntity.success(tbContentCustomPageInfo);
	}

	@RequestMapping("/delete/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		tbContentService.deleteById(id);
		return ResponseEntity.success();
	}

	@RequestMapping("/add")
	public ResponseEntity add(TbContent tbContent) {
		tbContentService.insert(tbContent);
		return ResponseEntity.success();
	}

	@RequestMapping("/update")
	public ResponseEntity update(TbContent tbContent) {
		tbContentService.update(tbContent);
		return ResponseEntity.success();
	}
}
