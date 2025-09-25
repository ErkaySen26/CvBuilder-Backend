package cv_maker.main.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "ddcfnt2qq");
        config.put("api_key", "941563596851621");
        config.put("api_secret", "S5xKzjC_zj8AtT7kRYgptpSxRHE");

        return new Cloudinary(config);
    }
}
