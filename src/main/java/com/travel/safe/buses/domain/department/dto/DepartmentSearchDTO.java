package com.travel.safe.buses.domain.department.dto;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestParam;

public record DepartmentSearchDTO(@RequestParam(required = false) String departmentName,
                                  @PageableDefault Pageable pageable) {

}