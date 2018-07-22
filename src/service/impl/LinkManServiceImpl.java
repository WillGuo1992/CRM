package service.impl;

import dao.LinkManDao;
import domain.LinkMan;
import service.LinkManService;

/**
 * @description:
 * @author: Will.Guo
 * @create: 2018-07-22 16:55
 **/
public class LinkManServiceImpl implements LinkManService {
    private LinkManDao linkManDao;

    public void setLinkManDao(LinkManDao linkManDao) {
        this.linkManDao = linkManDao;
    }
}
