package summer.jaffer.java.proxy;

import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {

        // 静态代理部分
//        IronManMovie ironManMovie = new IronManMovie();
//        Movie proxy = new CinemaProxy(ironManMovie);
//        proxy.play();

        // 动态代理部分
        IronManMovie ironManMovie = new IronManMovie();
        CinemaHandler cinemaHandler = new CinemaHandler(ironManMovie);
        Movie movie = (Movie) Proxy.newProxyInstance(IronManMovie.class.getClassLoader(), ironManMovie.getClass().getInterfaces(), cinemaHandler);
        movie.play();




    }
}
