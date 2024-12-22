package com.kaiyu.controller;

import com.kaiyu.domain.vo.DormLog;
import com.kaiyu.service.IDormLogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaiyu.common.domain.PageR;
import com.kaiyu.common.domain.R;
import com.kaiyu.domain.dto.DomLogDTO;

@RestController
@RequestMapping({"/log"})
public class DormLogController {
  @Autowired
  private IDormLogService dormLogService;
  
  @PostMapping("/list")
  public PageR<DormLog> list(@RequestBody DomLogDTO dto) {
    return dormLogService.selectDormLogList(dto);
  }

  @GetMapping("/all")
  public R<List<DormLog>> getAll() {
    return R.ok(dormLogService.selectDormLogAll());
  }
  
  @GetMapping("/{id}")
  public R<DormLog> getInfo(@PathVariable("id") Long id) {
    return R.ok(dormLogService.selectDormLogById(id));
  }

  @PostMapping
  public R<Void> add(@RequestBody DormLog dormLog) {
    dormLogService.insertDormLog(dormLog);
    return R.ok();
  }


  @PutMapping
  public R<Void> edit(@RequestBody DormLog dormLog) {
    dormLogService.updateDormLog(dormLog);
    return R.ok();
  }


  @DeleteMapping("/{ids}")
  public R<Void> remove(@PathVariable Long[] ids) {
    dormLogService.deleteDormLogByIds(ids);
    return R.ok();
  }
}

