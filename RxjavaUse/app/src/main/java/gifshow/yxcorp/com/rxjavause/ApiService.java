package gifshow.yxcorp.com.rxjavause;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

  @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20erveybody")
  Observable<Translation> getAcibaTranslation();
}
