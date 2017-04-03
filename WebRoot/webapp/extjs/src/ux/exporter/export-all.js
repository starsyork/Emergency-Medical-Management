/**
 * Created by IntelliJ IDEA.
 * User: lanjs
 * Date: 2012-03-12
 * Time: ����8:31
 * �������� ExtJS-Excel������Ҫ�� .js ���� ExtJSģ��
 *
 * ���ļ���,����Ŀ¼�ṹ��Ҫ�����޸�   ��������&������ϵ Button.js
 */


(function() {

    var scripts = document.getElementsByTagName('script'),
        host = window.location.hostname,
        path, i, ln, scriptSrc, match;

    for (i = 0,ln = scripts.length; i < ln; i++) {
        scriptSrc = scripts[i].src;

        match = scriptSrc.match(/export-all\.js$/);

        if (match) {
            path = scriptSrc.substring(0, scriptSrc.length - match[0].length);
            break;
        }
    }


    Ext.Loader.setConfig({ enabled: true });
//    Ext.Loader.setPath('Ext.ux.exporter', path + 'exporter');
    Ext.Loader.setPath('Ext.ux.exporter', path);
    Ext.require([
        'Ext.grid.*',
        'Ext.data.*',
        'Ext.util.*',
        'Ext.ux.exporter.Exporter'
    ]);

    document.write('<script type="text/javascript" src="' + path + 'downloadify.min.js"></script>');
    document.write('<script type="text/javascript" src="' + path + 'swfobject.js"></script>');
})();