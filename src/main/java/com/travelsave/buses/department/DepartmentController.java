package com.travelsave.buses.department;

import com.travelsave.buses.department.model.Department;
import com.travelsave.buses.department.model.DepartmentDTO;
import com.travelsave.buses.department.model.UpdatedDepartmentCommand;
import com.travelsave.buses.department.services.CreateDepartmentService;
import com.travelsave.buses.department.services.DeleteDepartmentService;
import com.travelsave.buses.department.services.GetDepartmentsService;
import com.travelsave.buses.department.services.GetDepartmentService;
import com.travelsave.buses.department.services.UpdateDepartmentService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

  private final CreateDepartmentService createDepartmentService;

  private final DeleteDepartmentService deleteDepartmentService;

  private final GetDepartmentsService getDepartmentsService;
  private final GetDepartmentService searchDepartmentByIdService;

  private final UpdateDepartmentService updateDepartmentService;

  public DepartmentController(CreateDepartmentService createDepartmentService,
      DeleteDepartmentService deleteDepartmentService, GetDepartmentsService getDepartmentsService,
      GetDepartmentService searchDepartmentByIdService,
      UpdateDepartmentService updateDepartmentService) {
    this.createDepartmentService = createDepartmentService;
    this.deleteDepartmentService = deleteDepartmentService;
    this.getDepartmentsService = getDepartmentsService;
    this.searchDepartmentByIdService = searchDepartmentByIdService;
    this.updateDepartmentService = updateDepartmentService;
  }

  @PostMapping("/department")
  public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody Department department) {
    return createDepartmentService.execute(department);
  }

  @GetMapping("/departments")
  public ResponseEntity<List<DepartmentDTO>> getDepartments() {
    return getDepartmentsService.execute(null);
  }

  @GetMapping("/department/{id}")
  public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable Integer id) {
    return searchDepartmentByIdService.execute(id);
  }

  @PutMapping("/department/update/{id}")
  public ResponseEntity<DepartmentDTO> UpdateDepartment(@PathVariable Integer id,
      @RequestBody Department department) {
    return updateDepartmentService.execute(new UpdatedDepartmentCommand(id, department));
  }

  @DeleteMapping("/department/delete/{id}")
  public ResponseEntity<Void> removeDepartment(@PathVariable Integer id) {
    return deleteDepartmentService.execute(id);
  }
}
