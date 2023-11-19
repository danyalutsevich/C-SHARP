package step.learning.dto.models;

import step.learning.services.formParse.FormParseResult;

import java.util.Map;

public class CallMeModel {

    public String phoneNumber;
    public String name;
    public String email;

    public CallMeModel(FormParseResult res) {
        Map<String, String> fields = res.getFields();
        this.phoneNumber = fields.get("phoneNumber");
        this.name = fields.get("name");
        this.email = fields.get("email");
    }

}
