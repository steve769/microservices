package info.stephenderrick.user.service;

import info.stephenderrick.user.VO.Department;
import info.stephenderrick.user.VO.ResponseTemplateVO;
import info.stephenderrick.user.entity.User;
import info.stephenderrick.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findUserByUserId(userId);
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" +
                        user.getDepartmentId(),
                Department.class);

        vo.setUser(user);
        vo.setDepartment(department);
        return vo;
    }
}
