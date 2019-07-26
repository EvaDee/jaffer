package summer.jaffer.java.proxy;

import summer.jaffer.log.LogUtils;

/**
 * 静态代理，用来播放电影
 * 增强play()方法
 * 1. 在播放电影前要准备爆米花
 * 2. 播放结束带走垃圾
 *
 */
public class CinemaProxy implements Movie{

    private Movie movie;

    CinemaProxy(Movie movie) {
        this.movie = movie;
    }

    @Override
    public void play() {
        before();
        movie.play();
        after();
    }

    private void before() {
        LogUtils.sout("静态代理 准备爆米花");
    }

    private void after() {
        LogUtils.sout("静态代理 带走垃圾");
    }
}
