package web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import domain.Customer;
import domain.PageBean;
import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import service.CustomerService;
import utils.UploadUtils;

import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @author: Will.Guo
 * @create: 2018-07-22 09:32
 **/
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

    private Customer customer = new Customer();
    @Override
    public Customer getModel() {
        return customer;
    }

    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    // 使用set方法的方式接收数据:
    private Integer currPage = 1;

    public void setCurrPage(Integer currPage) {
        if (currPage == null) {
            currPage = 1;
        }
        this.currPage = currPage;
    }

    // 使用set方法接受每页显示记录数
    private Integer pageSize = 3;

    public void setPageSize(Integer pageSize) {
        if (pageSize == null) {
            pageSize = 3;
        }
        this.pageSize = pageSize;
    }
    public String findAll() {
        // 接收参数：分页参数
        // 最好使用DetachedCriteria对象（条件查询--带分页）
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
        // 设置条件：（在web层设置条件）
        if (customer.getCust_name() != null) {
            // 输入名称:
            detachedCriteria.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
        }
        if (customer.getBaseDictSource() != null) {
            if (customer.getBaseDictSource().getDict_id() != null
                    && !"".equals(customer.getBaseDictSource().getDict_id())) {
                detachedCriteria
                        .add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
            }

        }
        if (customer.getBaseDictLevel() != null) {
            if (customer.getBaseDictLevel().getDict_id() != null
                    && !"".equals(customer.getBaseDictLevel().getDict_id())) {
                detachedCriteria
                        .add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
            }
        }
        if (customer.getBaseDictIndustry() != null && customer.getBaseDictIndustry().getDict_id() != null) {
            if (customer.getBaseDictIndustry().getDict_id() != null
                    && !"".equals(customer.getBaseDictIndustry().getDict_id())) {
                detachedCriteria
                        .add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
            }
        }
        PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria, currPage, pageSize);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";
    }

    public String saveUI() {
        return "saveUI";
    }


    /**
     * 文件上传提供的三个属性:
     */
    private String uploadFileName; // 文件名称
    private File upload; // 上传文件
    private String uploadContentType; // 文件类型

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String save() throws IOException {
        // 上传图片:
        if (upload != null) {
            // 文件上传：
            // 设置文件上传路径:
            String path = "./upload";
            // 一个目录下存放的相同文件名：随机文件名
            String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
            // 一个目录下存放的文件过多：目录分离
            String realPath = UploadUtils.getPath(uuidFileName);
            // 创建目录:
            String url = path + realPath;
            File file = new File(url);
            if (!file.exists()) {
                file.mkdirs();
            }
            // 文件上传:
            File dictFile = new File(url + "/" + uuidFileName);
            FileUtils.copyFile(upload, dictFile);
            // 设置image的属性的值:
            customer.setCust_image(url + "/" + uuidFileName);
        }
        customerService.save(customer);
        return "saveSuccess";
    }

    public String edit() {
        customer = customerService.findById(customer.getCust_id());
        ActionContext.getContext().getValueStack().push(customer);
        return "editSuccess";
    }

    public String update() throws IOException {
        // 文件项是否已经选择：如果选择了，就删除原有文件，上传新文件。如果没有选，使用原有即可。
        if (upload != null) {
            // 已经选择了:
            // 删除原有文件:
            String cust_image = customer.getCust_image();
            if (cust_image != null || !"".equals(cust_image)) {
                File file = new File(cust_image);
                file.delete();
            }
            // 文件上传：
            // 设置文件上传路径:
            String path = "C:/upload";
            // 一个目录下存放的相同文件名：随机文件名
            String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
            // 一个目录下存放的文件过多：目录分离
            String realPath = UploadUtils.getPath(uuidFileName);
            // 创建目录:
            String url = path + realPath;
            File file = new File(url);
            if (!file.exists()) {
                file.mkdirs();
            }
            // 文件上传:
            File dictFile = new File(url + "/" + uuidFileName);
            FileUtils.copyFile(upload, dictFile);

            customer.setCust_image(url + "/" + uuidFileName);
        }
        customerService.update(customer);
        return "updateSuccess";
    }

    public String delete() {
        customer = customerService.findById(customer.getCust_id());
        String cust_image = customer.getCust_image();
        if (cust_image != null && !"".equals(cust_image)) {
            File file = new File(cust_image);
            if (file.exists()) {
                file.delete();
            }
        }
        customerService.delete(customer);
        return "deleteSuccess";
    }


}
