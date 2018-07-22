package web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import domain.LinkMan;
import service.LinkManService;

/**
 * @description:
 * @author: Will.Guo
 * @create: 2018-07-22 16:53
 **/
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
    private LinkMan linkMan = new LinkMan();

    @Override
    public LinkMan getModel() {
        return null;
    }

    private LinkManService linkManService;

    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
    }
}
