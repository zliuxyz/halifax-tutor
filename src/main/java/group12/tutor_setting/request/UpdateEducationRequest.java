package group12.tutor_setting.request;

public class UpdateEducationRequest {
    private String token;
    private String education;

    public void setToken(String token) {
        this.token = token;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getToken() {
        return token;
    }

    public String getEducation() {
        return education;
    }
}