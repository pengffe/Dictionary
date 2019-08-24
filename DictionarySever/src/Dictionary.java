import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
//import lombok.Data;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Dictionary {
    private String word;
    private String meaning;
    private Map<String, String> map = new ConcurrentHashMap<>();

    Dictionary(){};

    /**
     * 读取文件数据加入到map缓存中
     * @throws IOException
     */
    public void readJsonData() throws IOException {
        ClassPathResource resource = new ClassPathResource("./dic.json");
        File file = resource.getFile();
        String jsonString = FileUtils.readFileToString(file, "UTF-8");

        JSONObject jsonObject = JSONObject.parseObject(jsonString);

        Set<String> keySet = jsonObject.keySet();
        for (String s : keySet) {
            String meaning = jsonObject.getString(s);
            map.put(s, meaning);
        }
    }
    public Map<String, String> getMap() {
        return map;
    }
}

