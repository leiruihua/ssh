/**
 * @author 雷瑞铧
 * @version 1.0.0
 * @filename UserAction.java
 * @time 2017年3月9日 下午7:20:40
 * @copyright(C) 2017 深圳市北辰德科技股份有限公司
 */
package com.beichende.assess.trade.web.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.beichende.assess.trade.pojo.User;
import com.beichende.assess.trade.pojo.VagueQueryUser;
import com.beichende.assess.trade.service.IUserService;
import com.beichende.assess.trade.web.dto.UserDto;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 用于执行用户流程控制的Action
 * <功能详细描述>
 *
 * @see
 */
@Scope(value = "prototype")//默认是singleton单例,只存在一个bean实例，改为prototype为多例
@Controller(value = "userAction")////每一个请求过来就生成一个userAction实例
public class UserAction extends ActionSupport implements ServletRequestAware {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(UserAction.class.getName());
    private HttpServletRequest request;
    private Long total;                //总数
    private Integer pageSize;            //分页大小 默认值
    private Integer pageNumber;            //分页数 默认值
    private List<UserDto> userList;        //用户列表

    private User user;
    private User user2;

    private String userNo;
    //批量删除
    private String ids;
    //模糊查询
    private String userName = "";
    private String userSex = "";
    private String userAgeStart = "";
    private String userAgeStop = "";
    private String userPhone = "";
    //提示消息
    private String msg;

    private IUserService userService;

    public UserAction() {
        logger.info("创建UserAction实例");
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    public String list() {
        String pageStr = request.getParameter("page");
        String rowsStr = request.getParameter("rows");
        String sort = request.getParameter("sort");
        String order = request.getParameter("order");
        VagueQueryUser vagueQueryUser = new VagueQueryUser();
        BeanUtils.copyProperties(this, vagueQueryUser);//将前台获取的属性复制一份给vagueQueryUser对象
        try {
            pageSize = pageStr == null ? 1 : Integer.parseInt(pageStr);

            pageNumber = rowsStr == null ? 5 : Integer.parseInt(rowsStr);

            userList = userService.list(vagueQueryUser, pageSize, pageNumber, sort, order);
            total = userService.portionList(vagueQueryUser);
        } catch (Exception e) {
            logger.error("list方法发生异常", e);
        }
        return SUCCESS;
    }

    /**
     * 增加用户
     *
     * @return
     */
    public String addUser() {
        try {
            userService.addUser(user);
            msg = "操作成功";
            return "operateSuccess";
        } catch (Exception e) {
            logger.error("addUser()异常信息{}" + e);
            msg = "操作失败";
            return "operateError";
        }
    }

    /**
     * 批量删除用户
     *
     * @return
     */
    public String deleteUser() {

        try {
            userService.deleteUser(ids);
            msg = "操作成功";
            return "operateSuccess";
        } catch (Exception e) {
            logger.error("deleteUser异常信息{}" + e);
            msg = "操作失败";
            return "operateError";
        }
    }

    /**
     * 修改单个用户
     *
     * @return
     */
    public String editAngleUser() {
        try {
            userService.editAngleUser(user2);
            msg = "操作成功";
            return "operateSuccess";
        } catch (Exception e) {
            logger.error("editAngleUser()异常信息{}" + e);
            msg = "操作失败";
            return "operateError";
        }
    }


    @Required//表示此属性必须注入，在读取spring配置时候会检查是否已经注入，如果没注入会报错，而不是在运行时才发现没注入才报错
    //@Autowired//注入方式一：按byType类型注入，如果同个类型有多个会报错，不建议使用
    @Resource(name = "userService")//默认是byName，找不到对应的名字就按byType类型注入
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }


    @JSON(name = "rows")
    public List<UserDto> getUserList() {
        return userList;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Long getTotal() {
        return total;
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserAgeStart() {
        return userAgeStart;
    }

    public void setUserAgeStart(String userAgeStart) {
        this.userAgeStart = userAgeStart;
    }

    public String getUserAgeStop() {
        return userAgeStop;
    }

    public void setUserAgeStop(String userAgeStop) {
        this.userAgeStop = userAgeStop;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
