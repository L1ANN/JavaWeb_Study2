package service;

import dao.PrivilegeDao;
import dao.ResourceDao;
import dao.RoleDao;
import dao.UserDao;
import domain.Privilege;
import domain.Resource;
import domain.Role;
import domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 11:49 2017/12/10
 * @Modified By:
 */
public class SecurityService {

    private ResourceDao resourceDao = new ResourceDao();
    private PrivilegeDao privilegeDao = new PrivilegeDao();
    private RoleDao roleDao = new RoleDao();
    private UserDao userDao = new UserDao();

    /********************************提供资源相关服务******************************************/
    public void addResource(Resource r){
        resourceDao.add(r);
    }

    public Resource findResource(String uri){
        return resourceDao.find(uri);
    }

    public Resource findResourceById(String id){
        return resourceDao.findById(id);
    }

    public List<Resource> getAllRecource(){
        return resourceDao.getAll();
    }

    public void updateResourcePrivilege(String resourceId,String privilegeId){
        Resource r = resourceDao.findById(resourceId);
        Privilege p = privilegeDao.find(privilegeId);
        resourceDao.updatePrivilege(r,p);
    }
    /****************************************************************************************/

    /********************************提供权限相关服务******************************************/
    public void addPrivilege(Privilege p){
        privilegeDao.add(p);
    }

    public Privilege findPrivilege(String id){
        return privilegeDao.find(id);
    }

    public List<Privilege> getAllPrivilege(){
        return privilegeDao.getAll();
    }
    /****************************************************************************************/

    /********************************提供角色相关服务******************************************/
    public void addRole(Role role){
        roleDao.add(role);
    }

    public Role findRole(String id){
        return roleDao.find(id);
    }

    public List<Role> getAllRole(){
        return roleDao.getAll();
    }

    public void updateRolePrivileges(String roleId,String[] privilegeIds){
        Role role = roleDao.find(roleId);
        List<Privilege> list = new ArrayList<Privilege>();
        for(int i = 0 ; privilegeIds!=null&&i<privilegeIds.length;i++){
            Privilege p = privilegeDao.find(privilegeIds[i]);
            list.add(p);
        }
        roleDao.updatePrivilege(role,list);
    }
    /****************************************************************************************/

    /********************************提供用户相关服务******************************************/
    public void addUser(User user){
        userDao.add(user);
    }

    public User findUser(String id){
        return userDao.find(id);
    }

    public User findUser(String username,String password){
        return userDao.find(username,password);
    }

    public List<User> getAllUser(){
        return userDao.getAll();
    }

    //更新用户角色
    public void updateUserRole(String userId,String[] roleIds){
        User user = userDao.find(userId);
        List<Role> list = new ArrayList<>();
        for(int i =0;roleIds!=null&&i<roleIds.length;i++){
            Role r = roleDao.find(roleIds[i]);
            list.add(r);
        }
        userDao.updateUserRoles(user,list);
    }

    //得到某个用户拥有的所有去权限
    public List<Privilege> getUserAllPrivilege(String userId){
        List<Privilege> allPrivilege = new ArrayList<>();

        User user = userDao.find(userId);
        Set<Role> roles = user.getRoles();//获取用户所拥有的角色，这里角色中没有权限

        //根据角色来查找权限
        for(Role r:roles){
            r = roleDao.find(r.getId());
            Set<Privilege> privileges = r.getPrivileges();
            allPrivilege.addAll(privileges);
        }
        return allPrivilege;
    }
    /****************************************************************************************/
}
