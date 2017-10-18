package com.flymvc.config;

import java.util.ArrayList;
import java.util.List;

import com.flymvc.core.BootStrap;
import com.flymvc.plugin.Plugin;
import com.flymvc.render.JspRender;
import com.flymvc.render.Render;
import com.flymvc.route.RouteMatcher;

public class Fly {
	
	/**
	 * 默认配置
	 */
	private FlyConfig flyConfig;
	
	/**
	 * 路由
	 */
	private RouteMatcher routeMatcher;
	/**
	 * 渲染器
	 */
	private Render render;
	
	/**
	 * 插件
	 */
	private List<Plugin> plugins;
	
	
	private static Fly  fly = null;
	
	
	public FlyConfig getFlyConfig() {
		return flyConfig;
	}

	public void setFlyConfig(FlyConfig flyConfig) {
		this.flyConfig = flyConfig;
	}

	public RouteMatcher getRouteMatcher() {
		return routeMatcher;
	}

	public void setRouteMatcher(RouteMatcher routeMatcher) {
		this.routeMatcher = routeMatcher;
	}

	public Render getRender() {
		return render;
	}

	public void setRender(Render render) {
		this.render = render;
	}
	
	public List<Plugin> getPlugins() {
		return plugins;
	}

	public void setPlugins(List<Plugin> plugins) {
		this.plugins = plugins;
	}

	/**
	 * 私有化构造
	 */
	private Fly() {
		this.flyConfig = new FlyConfig();
		this.routeMatcher = new RouteMatcher();
		this.render = new JspRender();
		this.plugins = new ArrayList<Plugin>();
	}
	
	/**
	 * 获取单例对象
	 * @param bootStrap 
	 * @return
	 */
	public static Fly init(BootStrap bootStrap){
		if(fly== null){
			fly = new Fly();
			bootStrap.config(fly.getFlyConfig());
			bootStrap.plugin(fly.getPlugins());
			bootStrap.route(fly.getRouteMatcher());
			fly.setRender(bootStrap.render());
		}
		return fly;
	}
	public static Fly me(){
		if(fly==null){
			throw new RuntimeException("fly is null");
		}
		return fly;
	}
}