package net.jeeshop.web.action.kindeditor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by dylan on 15-5-21.
 */
@Controller
@RequestMapping("editor")
public class KindController {

    @RequestMapping("upload")
    public String uploadFile(MultipartFile file) {
        return null;
    }
}
