// Store类封装了一个客户端缓存,用于存储 Model 对象. Stores 通过一个代理 Proxy 来加载数据, 并提供函数来 排序, 过滤 以及查询 内部所包含的 model 实例.
//创建Store非常简单 - 我们只需要传入 Model 以及用来加载/保存 数据的Proxy作为配置项即可

Ext.define('Wj.store.admin.AttdocStatis',{
    extend:'Ext.data.Store',
    requires:['Wj.model.admin.AttdocStatis'],
    model:'Wj.model.admin.AttdocStatis',
  
  	storeId: 'admin.AttdocStatis',
  	//当前store对象的唯一标识ID. 当此值存在时, 当前Store将被注册到Ext.data.StoreManager中, 从而可以在别处轻松创建.
    autoLoad: false,
    //如果data属性未定义, 并且autoLoad值为'true'或对象, 则此store的load方法将在创建后自动执行.
    //如果autoLoad值是一个对象, 这个对象将 作为参数传递给load方法. 默认为'false'.

    // have to override proxy
    proxy: {
		type: 'ajax',
		//url: 'data/test/admin/attdocstatistic/read.json',
		url: Wj.url.GetAttdocStatis,
		reader: {
			type: 'json',
			root: 'results',
			successProperty: 'success',
			totalProperty: 'total'
		}
	},
//一个配置对象，包含一个或多个事件处理函数，在对象初始化时添加到对象。 它应该是addListener指定的一个有效的监听器配置对象， 一次添加多个事件处理函数。
	listeners: {
		//load:通过配置的 proxy 加载数据到Store 中. 使用 Proxy 来执行异步调用,不关心具体的Proxy是什么类型,
		//自动添加获得的实例到 Store 中,如有需要,会调用传入的回调函数. 用法示例如下:
		load: function(store, record, success){
			if(!success){
				var r = store.getProxy().getReader().rawData;
				Wj.util.handleServerFailure(r);
			}
		}
	}

});