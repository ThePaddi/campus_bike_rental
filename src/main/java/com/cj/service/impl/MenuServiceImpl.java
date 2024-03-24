package com.cj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cj.common.MenuHelper;
import com.cj.entity.Menu;
import com.cj.entity.vo.MetaVo;
import com.cj.entity.vo.RouterVo;
import com.cj.mapper.MenuMapper;
import com.cj.service.MenuService;
import com.cj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private UserService userService;


    @Override
    public List<Integer> getMenuIdsByRoleId(Integer roleId) {
        List<Integer> menuIds = null;
        menuMapper.getMenuIdsByRoleId(roleId);
        return menuIds;
    }

    public List<Menu> findNodes() {
        List<Menu> sysMenuList = this.list();
        if (sysMenuList == null){
            return null;
        }

        //构建树形数据
        List<Menu> result = MenuHelper.buildTree(sysMenuList);
        return result;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(Menu menu) {
        String routerPath = "/" + menu.getPath();
        if(menu.getParentId().intValue() != 0) {
            routerPath = menu.getPath();
        }
        return routerPath;
    }

    //查询按钮
    public List<String> findUserPermsByUserId(Integer userId) {
        List<Menu> sysMenuList = null;
        //判断是否是管理员，如果是管理员，查询所有按钮列表
        if (userId.intValue() == 1){
            sysMenuList = this.list();
        }else {
            //如果不是管理员，根据userId查询可以操作的按钮列表
            sysMenuList = baseMapper.findMenuListByUserId(userId);
        }
        List<String> permsList = sysMenuList.stream().filter(item -> item.getType() == 4)
                .map(item -> item.getPerm())
                .collect(Collectors.toList());
        //从查询出来的数据里面获取可以操作按钮值的list集合，返回
        return permsList;
    }

    private List<RouterVo> buildRouter(List<Menu> menus,Integer userId) {
        //创建list集合存储最终数据
        List<RouterVo> routers = new ArrayList<>();
        for (Menu menu : menus) {
            RouterVo router = new RouterVo();
            router.setPath(getRouterPath(menu));
            router.setComponent(menu.getComponent());
            router.setRedirect(menu.getRedirect());
            //router.setName(menu.getName());
            router.setMeta(new MetaVo(menu.getName(), menu.getIcon(),false,userService.getRolesIdByUserId(userId)));
            //下一层数据部分
            List<Menu> children = menu.getChildren();
            if (menu.getType().intValue() == 4){
                //加载下面的隐藏路由
                List<Menu> hiddenMenuList = children.stream().filter(item -> StringUtils.isEmpty(item.getComponent()))
                        .collect(Collectors.toList());
                for (Menu hiddenMenu : hiddenMenuList) {
                    RouterVo hiddenRouter = new RouterVo();
                    hiddenRouter.setPath(getRouterPath(hiddenMenu));
                    hiddenRouter.setComponent(hiddenMenu.getComponent());
                    router.setRedirect(menu.getRedirect());
                    router.setMeta(new MetaVo(menu.getName(), menu.getIcon(),false,userService.getRolesIdByUserId(userId)));
                    routers.add(hiddenRouter);
                }
            }else {
                router.setChildren(buildRouter(children,userId));
            }
            routers.add(router);
        }
        return routers;
    }

    @Override
    public List<RouterVo> findUserMenuListByUserId(Integer userId) {
        List<Menu> sysMenuList = null;
        //1.判断当前用户是否是管理员，userId=1的是管理员
        //1.1如果是管理员查询所有菜单列表
        if (userId.longValue() == 1){
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.orderByAsc(Menu::getSort);
            sysMenuList = baseMapper.selectList(queryWrapper);
        }else {
            //1.2如果不是管理员，根据userId查询可以操作的菜单列表
            sysMenuList = baseMapper.findMenuListByUserId(userId);
        }
        //2.把查询出来的数据构建成框架要求的路由结构
        //使用菜单操作工具类构建树形结构
        List<Menu> sysMenuTreeList = MenuHelper.buildTree(sysMenuList);
        //构建成框架要求的路由结构
        List<RouterVo> routerList = this.buildRouter(sysMenuTreeList,userId);
        return routerList;
    }

    @Override
    public List<String> findMenusByUserId(Integer userId) {
        List<String> menus = menuMapper.findMenusByUserId(userId);
        return menus;
    }
}
