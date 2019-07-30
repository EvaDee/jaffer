package summer.jaffer.java.proxy;

import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {

        IronManMovieImpl ironManMovieImpl = new IronManMovieImpl();

//        // 静态代理部分
//        Movie proxy = new CinemaProxy(ironManMovieImpl);
//        proxy.play();

        // 动态代理部分
        CinemaHandler movieHandler = new CinemaHandler(ironManMovieImpl);
        // 该movie对象为动态生成的代理类的对象，代理类由jdk生成字节码文件
        Movie movie = (Movie) Proxy.newProxyInstance(IronManMovieImpl.class.getClassLoader(), ironManMovieImpl.getClass().getInterfaces(), movieHandler);
        movie.play();

        ProfessorSpeechImpl professorSpeech = new ProfessorSpeechImpl();
        CinemaHandler speechHandler = new CinemaHandler(professorSpeech);
        Speech speech = (Speech) Proxy.newProxyInstance(ProfessorSpeechImpl.class.getClassLoader(), professorSpeech.getClass().getInterfaces() ,speechHandler);
        speech.giveSpeach();
    }
}
