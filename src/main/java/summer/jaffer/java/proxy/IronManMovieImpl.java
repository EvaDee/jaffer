package summer.jaffer.java.proxy;

import summer.jaffer.log.LogUtils;

/**
 * Iron Man Movie
 * Movie实现类，需要被代理的实际类
 */
public class IronManMovieImpl implements Movie{

    @Override
    public void play() {
        LogUtils.sout("Iron Man Movie : I am Stack ");
    }
}
