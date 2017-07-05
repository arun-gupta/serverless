package org.sample.serverless.aws.rds;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author arungupta
 */
public class EmployeeHandler implements RequestHandler<Request, String> {

    @Override
    public String handleRequest(Request request, Context context) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Employee employee = new Employee();
            employee.setId(request.id);
            employee.setName(request.name);
            session.save(employee);
            session.getTransaction().commit();
        }

        return String.format("Added %s %s.", request.id, request.name);
    }
}
