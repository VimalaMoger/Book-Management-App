package com.example.demo.ServiceImpl;
import com.example.demo.exception.EmployeeNotFoundException;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.SelectionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EntityManager em;

	@Transactional
	@Override
	public List<Employee> saveEmployeeInfo(Employee emp) {
		Session session= em.unwrap(Session.class);
		session.persist(emp);
		return getAllEmployees();
	
	}
	@Transactional
	@Override
	public List<Employee> getAllEmployees() {
		Session  session = em.unwrap(Session.class);
		//createSelectionQuery method with a HQL query String
		SelectionQuery<Employee> query = session.createSelectionQuery("From Employee", Employee.class);
		List<Employee> employees = query.getResultList();
		return employees;

	}
	@Transactional
	@Override
	public Optional<Employee> getEmployeeById(Long id) {
		Session  session = em.unwrap(Session.class);
		//createSelectionQuery method with a HQL query String
        SelectionQuery<Employee> query = session.createSelectionQuery("FROM Employee WHERE id=?1",Employee.class);
		//Query with positional parameter
		query.setParameter(1, id);
		Employee emp = query.getResultStream().findFirst().orElse(null);
        return Optional.ofNullable(emp);
	}
	@Transactional
	@Override
	public Employee updateEmployee(Employee emp, Long eid) {
		Session  session = em.unwrap(Session.class);
		//createSelectionQuery method with a JPQL query String
		MutationQuery query  = session.createNativeMutationQuery("UPDATE employees set first_name=:firstname, last_name=:lastname, email=:email1 WHERE id=:id");
		//Query with named parameter
		query.setParameter("firstname",emp.getFirstName());
		query.setParameter("lastname", emp.getLastName());
		query.setParameter("email1", emp.getEmail());
		query.setParameter("id", eid);
        int result = query.executeUpdate();
		if(result > 0)
			return getEmployeeById(eid).get();
		else
			throw new EmployeeNotFoundException("Employee not found");
	}

	@Transactional
	@Override
	public void deleteEmployee(Long id) {
		Session  session = em.unwrap(Session.class);
		MutationQuery query  = session.createNativeMutationQuery("DELETE FROM employees WHERE id =?1");
		//Query with positional parameter
		query.setParameter(1, id);
		int rowCount = query.executeUpdate();
        if(rowCount < 1)
            throw new EmployeeNotFoundException("Employee not found");
		}


}
