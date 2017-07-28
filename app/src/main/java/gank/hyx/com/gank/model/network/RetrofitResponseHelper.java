package gank.hyx.com.gank.model.network;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Response;

public class RetrofitResponseHelper {

    private JSONObject jsonObject;
    private Boolean responseStatus = false;
    private String errorCode = "999999";
    private String errorMessage = "Response Json error.";

    public RetrofitResponseHelper(Response response) {

        if (response.isSuccessful() && response.errorBody() == null) {
            responseStatus = true;
        } else {
            try {
                String response_String = response.errorBody().string();
                jsonObject = new JSONObject(response_String);
                errorCode = jsonObject.get("status_code").toString();
                errorMessage = jsonObject.get("message").toString();
            } catch (IOException e) {
                e.printStackTrace();
                responseStatus = false;
                errorCode = String.valueOf(response.raw().code());
                errorMessage = response.raw().message();
                jsonObject = null;
            } catch (JSONException e) {
                e.printStackTrace();
                responseStatus = false;
                errorCode = String.valueOf(response.raw().code());
                errorMessage = response.raw().message();
                jsonObject = null;
            }
        }


    }

    /**
     * 获取返回数据状态（成功或失败）
     *
     * @return
     */
    public Boolean isResponseOK() {
        return responseStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
