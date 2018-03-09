package cn.lym;

import cn.lym.entity.NotFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author : liuymz
 * @date : Created in 2018/3/9 11:54
 */
@WebFilter(filterName = "login", urlPatterns = "/*")
public class LoginFilter implements Filter {
  private static List<NotFilter> notFilters;

  @Autowired private NotFilterRepository notFilterRepository;

  @Override
  public void init(FilterConfig filterConfig) {
    notFilters = notFilterRepository.findAll();
  }

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    String servletPath = request.getServletPath();
    System.out.println(servletPath);

    HttpSession session = request.getSession();
    String isLogin = (String) session.getAttribute("login");

    if (StringUtils.isEmpty(isLogin)) {
      if (isFilter(servletPath)) {
        response.sendRedirect("/login");
        return;
      }
    }

    filterChain.doFilter(servletRequest, servletResponse);
  }

  /**
   * 是不是要过滤
   *
   * @param path 访问的路径
   * @return true 需要
   */
  private boolean isFilter(String path) {

    for (NotFilter notFilter : notFilters) {
      //匹配过滤规则
      boolean flag = path.startsWith(notFilter.getPath()+"/") || path.equals(notFilter.getPath());
      if (flag) {
        return false;
      }
    }

    return true;
  }

  @Override
  public void destroy() {}
}
