package web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import domain.BaseDict;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;
import service.BaseDictService;

import java.io.IOException;
import java.util.List;


/**
 * @description:
 * @author: Will.Guo
 * @create: 2018-07-22 09:38
 **/
public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict> {
    private BaseDict baseDict = new BaseDict();
    @Override
    public BaseDict getModel() {
        return baseDict;
    }
    private BaseDictService baseDictService;

    public void setBaseDictService(BaseDictService baseDictService) {
        this.baseDictService = baseDictService;
    }

    public String findByTypeCode() throws IOException {
        List<BaseDict> list =  baseDictService.findByTypeCode(baseDict.getDict_type_code());
        JSONArray jsonArray = JSONArray.fromObject(list);

        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return NONE;
    }
}
