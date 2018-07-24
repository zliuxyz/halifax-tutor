package group12.search;

import group12.data_access.*;
import group12.exceptions.SearchQuerySQLException;
import group12.token_auth.JWTAccessToken;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

class SearchService {

    @Value("${search.auth}")
    private String auth;

    SearchResponse getSearchResponse(SearchRequest searchRequest) {
        String school = searchRequest.getSchool();
        String courseName = searchRequest.getCourseName();
        TutorPublicInfoDAO tutorPublicInfoDAO = new TutorPublicInfoDaoImpl();

        boolean success;
        int numOfResults;
        List<TutorPublicInfo> results;

        SearchResponse searchResponse;

        try {
            results = tutorPublicInfoDAO.getTutorPublicInfo(school, courseName);
            success = true;
            numOfResults = results.size();

            searchResponse = new SearchResponse(success, numOfResults, results);
        } catch (SearchQuerySQLException e) {
            results = new ArrayList<>();
            success = false;
            numOfResults = 0;

            searchResponse = new SearchResponse(success, numOfResults, results);
        }

        return searchResponse;
    }

    IdentityResponse getSearchIdentity(IdentityRequest identityRequest) {
        IdentityResponse identityResponse = new IdentityResponse();
        if (!auth.equals("true")) {
            identityResponse.setSuccess(true);
            identityResponse.setType("any");
        } else {
            String token = identityRequest.getToken();
            String email = JWTAccessToken.getInstance().decodeToken(token);
            if (email == null) {
                identityResponse.setSuccess(false);
            } else {
                IDataAccessObject dataAccessObject = new MysqlDAOImpl();
                if (dataAccessObject.getStudentByEmail(email) != null) {
                    identityResponse.setType("student");
                } else if (dataAccessObject.getTutorByEmail(email) != null) {
                    identityResponse.setType("tutor");
                } else {
                    identityResponse.setType("admin");
                }
                identityResponse.setSuccess(true);
            }
        }
        return identityResponse;
    }
}
