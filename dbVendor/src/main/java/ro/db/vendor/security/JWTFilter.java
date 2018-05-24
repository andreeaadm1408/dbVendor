package ro.db.vendor.security;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ro.db.vendor.domain.Employees;
import ro.db.vendor.domain.UserType;
import ro.db.vendor.service.AuthService;

@CrossOrigin
@Service
@Log4j
public class JWTFilter implements HandlerInterceptor {

  private static String[] adminPathsArray = {
      "/db-vendor/feedback",
      "/db-vendor/feedback/details",
      "/db-vendor/feedback/getFeedbacksForVendor",
      "/db-vendor/feedback/addFeedback",
      "/db-vendor/feedback/NotifyAll",
      "/db-vendor/feedback/endProject",
      "/db-vendor/employee",
      "/db-vendor/employee/allMng",
      "/db-vendor/employee/addEmployee",
      "/db-vendor/employee/showAllEmployees",
      "/db-vendor/vendors",
      "/db-vendor/vendors/manager",
      "/db-vendor/vendors/detailOfVendor",
      "/db-vendor/vendors/getVendor",
      "/db-vendor/vendors/addVendor",
      "/db-vendor/vendors/getVendorManagers",
      "/db-vendor/vendors/updateVendor",
      "/db-vendor/vendors/allOriginMng",
      "/db-vendor/vendors/vendorsPerProject",
      "/db-vendor/vendors/addHoliday",
      "/db-vendor/vendors/getVendorDaysOffLeft",
      "/db-vendor/vendors/skills",
      "/db-vendor/vendors/addOvertime",
      "/db-vendor/vendors/vendorSkills",
      "/db-vendor/department/allDep",
      "/db-vendor/projects/addProject",
      "/db-vendor/employee/totalNumberOfEmployees",
      "/db-vendor/vendors/totalNumberOfVendors"
  };
  private static String[] managerPathsArray = {
      "/db-vendor/vendors",
      "/db-vendor/vendors/manager",
      "/db-vendor/vendors/detailOfVendor",
      "/db-vendor/feedback",
      "/db-vendor/feedback/details",
      "/db-vendor/feedback/getFeedbacksForVendor",
      "/db-vendor/feedback/addFeedback",
      "/db-vendor/feedback/NotifyAll",
      "/db-vendor/feedback/endProject"
  };
  private static String[] employeePathsArray = {
      "/db-vendor/employee",
      "/db-vendor/feedback/addFeedback"
  };
  private static String[] allowedURIsArray = {
      /* public links */
      "/db-vendor/employee/login"
  };
  private static List<String> managerPaths;
  private static List<String> employeePaths;
  private static List<String> adminPaths;
  private static List<String> allowedURIs;

  static {
    managerPaths = Arrays.asList(managerPathsArray);
    employeePaths = Arrays.asList(employeePathsArray);
    adminPaths = Arrays.asList(adminPathsArray);
    allowedURIs = Arrays.asList(allowedURIsArray);
  }

  @Autowired
  private AuthService authService;

  @Override
  public boolean preHandle(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o) throws Exception {

    AntPathMatcher matcher = new AntPathMatcher();
    for (String url : allowedURIs) {
      if (matcher.match(url, httpServletRequest.getRequestURI())) {
        return true;
      }
    }
    try {
      if ("OPTIONS".equals(httpServletRequest.getMethod())) {
        return true;
      }
      String token = httpServletRequest.getHeader("db-vendor-token");
      if (token == null) {
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        log.info("Request with token null!");
        return false;
      }
      Employees user = authService
          .authenticatedUser(token);

      int idRole = user.getRolesByIdRole().getIdRole();
      int admin = UserType.ADMIN.getIdRole();
      int manager = UserType.MANAGER.getIdRole();
      int employee = UserType.EMPLOYEE.getIdRole();
      String requestURI = httpServletRequest.getRequestURI();
      if ((idRole == admin && adminPaths.contains(requestURI))
          || ((idRole == manager) && managerPaths.contains(requestURI))
          || ((idRole == employee) && employeePaths.contains(requestURI))) {
        return true;
      }
      httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      log.info("This user is unauthorized to access this requestURI!");
      return false;
    } catch (Exception e) {
      httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
      log.error("This user is unauthorized to access this requestURI!", e);
      return false;
    }
  }

  @Override
  public void postHandle(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
      throws Exception {

  }

  @Override
  public void afterCompletion(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

  }

}
