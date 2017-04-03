/**
 * @class Ext.ux.Exporter.ExcelFormatter.Worksheet
 * @extends Object
 * Represents an Excel worksheet
 * @cfg {Ext.data.Store} store The store to use (required)
 */
Ext.define("Ext.ux.exporter.excelFormatter.Worksheet", {

  constructor: function(store, config) {
    config = config || {};

    this.store = store;

    Ext.applyIf(config, {
      hasTitle   : true,
      hasHeadings: true,
      stripeRows : true,
      title  : "表格导出",
      columns    : store.fields == undefined ? {} : store.fields.items
    });

    Ext.apply(this, config);

    Ext.ux.exporter.excelFormatter.Worksheet.superclass.constructor.apply(this, arguments);
  },

  /**
   * @property dateFormatString
   * @type String
   * String used to format dates (defaults to "Y-m-d"). All other data types are left unmolested
   */
  dateFormatString: "Y-m-d",

  worksheetTpl: new Ext.XTemplate(
    '<ss:Worksheet ss:Name="{title}">',
    
      //sheet的名称
      '<ss:Names>',
        '<ss:NamedRange ss:Name="Print_Titles" ss:RefersTo="=\'{title}\'!R1:R2" />',
      '</ss:Names>',
      
      //表格内容:列数、行数
      '<ss:Table x:FullRows="1" x:FullColumns="1" ss:ExpandedColumnCount="{colCount}" ss:ExpandedRowCount="{rowCount}">',
        '{columns}',
        
        //以下为表格顶部的大标题部分
        '<ss:Row ss:Height="38">',
            '<ss:Cell ss:StyleID="title" ss:MergeAcross="{colCount - 1}">',
              '<ss:Data xmlns:html="http://www.w3.org/TR/REC-html40" ss:Type="String">',
                '<html:B><html:U><html:Font html:Size="15">{title}',
                '</html:Font></html:U></html:B></ss:Data><ss:NamedCell ss:Name="Print_Titles" />',
            '</ss:Cell>',
        '</ss:Row>',
        
        //以下为表格标题
        '<ss:Row ss:AutoFitHeight="1">',
          '{header}',
        '</ss:Row>',
        
        '{rows}',//具体数据部分
        
      '</ss:Table>',
      //以下为其他属性
      '<x:WorksheetOptions>',
        '<x:PageSetup>',
          '<x:Layout x:CenterHorizontal="1" x:Orientation="Landscape" />',
          '<x:Footer x:Data="Page &amp;P of &amp;N" x:Margin="0.5" />',
          '<x:PageMargins x:Top="0.5" x:Right="0.5" x:Left="0.5" x:Bottom="0.8" />',
        '</x:PageSetup>',
        '<x:FitToPage />',
        '<x:Print>',
          '<x:PrintErrors>Blank</x:PrintErrors>',
          '<x:FitWidth>1</x:FitWidth>',
          '<x:FitHeight>32767</x:FitHeight>',
          '<x:ValidPrinterInfo />',
          '<x:VerticalResolution>600</x:VerticalResolution>',
        '</x:Print>',
        '<x:Selected />',
        '<x:DoNotDisplayGridlines />',
        '<x:ProtectObjects>False</x:ProtectObjects>',
        '<x:ProtectScenarios>False</x:ProtectScenarios>',
      '</x:WorksheetOptions>',
    '</ss:Worksheet>'
  ),

  /**
   * Builds the Worksheet XML
   * @param {Ext.data.Store} store The store to build from
   */
  render: function(store) {

    return this.worksheetTpl.apply({
        title   : this.title,
        colCount: this.columns.length,
//        colCount: this.effectColumnCount(),
      header  : this.buildHeader(),
      columns : this.buildColumns().join(""),
      rows    : this.buildRows().join(""),
      rowCount: this.store.getCount() + 2
    });
  },
  
  effectColumnCount:function(){
	  var cnt=0;
	  Ext.each(this.columns, function (col, dataIndex) {
          if (col.xtype == "rownumberer") return;
          var name  = col.name || col.dataIndex;

          if(name) {
        	  cnt++;
          }
	  }, this);
	  
	  alert(this.columns.length+",real="+cnt);
	  return cnt;
  },
  
  buildColumns: function() {
    var cols = [];

    Ext.each(this.columns, function(column) {
      cols.push(this.buildColumn());
    }, this);

    return cols;
  },

  buildColumn: function(width) {
    return Ext.String.format('<ss:Column ss:AutoFitWidth="1" ss:Width="{0}" />', width || 164);
  },

  buildRows: function() {
    var rows = [];

    this.store.each(function(record, index) {
      rows.push(this.buildRow(record, index));
    }, this);

    return rows;
  },

    buildHeader:function () {
    	var cells = [],
        insertCell=function(col){
	        var title;
	        if (col.text != undefined) {
	            title = col.text;
	        } else if (col.name) {
	            title = col.name.replace(/_/g, " ");
	            title = Ext.String.capitalize(title);
	        }
	        //console.log(title);
	        cells.push(Ext.String.format('<ss:Cell ss:StyleID="headercell"><ss:Data ss:Type="String">{0}</ss:Data><ss:NamedCell ss:Name="Print_Titles" /></ss:Cell>', title));
	    }
    	
//    	alert("buildHeader。列的数量:"+this.columns.length);
    	
        Ext.each(this.columns, function (col, dataIndex) {
            if (col.xtype == "rownumberer") return;
            var name  = col.name || col.dataIndex;

            if(name) {
                if (Ext.isArray(col.columns) && col.columns.length>0) {
                    Ext.each(col.columns, function (col, dataIndex) {
                        insertCell(col);
                    });
                }
                else{
                    insertCell(col);
                }
            }
            else{
            	//没有列标题的,可能是checkbox则不导出
            	 insertCell(col);
            }
            
        }, this);

        return cells.join("");
    },

  buildRow: function(record, index) {
    var style,
        cells = [];
    if (this.stripeRows === true) style = index % 2 == 0 ? 'even' : 'odd';
    
    var insertRow=function(col,record,me){
        var name  = col.name || col.dataIndex;

        if(name) {
            if (Ext.isFunction(col.renderer)) {
                var value = col.renderer(record.get(name), record, record),
                    type = "String";
            } else {
                var value = record.get(name), type  = me.typeMappings[col.type || record.fields.get(name).type.type];

            }
            if(me.expandTypeMapping[type]){
                value=value.display || value.vl;
                type=me.expandTypeMapping[type];
            }
           
//            alert("单元格:colName="+name+'|,colValue='+value+'|,colType='+type);
            cells.push(me.buildCell(value, type, style).render());
        }
        else{
        	//没有名字的列，但是标题行却存在，因此也必须添加一个cell，否则会无法对齐
        	//没有列标题的,可能是checkbox则不导出
        	cells.push(me.buildCell('', 'String', style).render());
        }
    };
    
//    alert("buildRow .列数="+this.columns.length);
    
    Ext.each(this.columns, function(col,index,self) {
        var me=this;
//        alert("准备insertRow:"+col.dataIndex);
        if(Ext.isArray(col.columns) && col.columns.length>0){
            Ext.each(col.columns, function(col) {
                insertRow(col,record,me);
            })
        }
        else{
            insertRow(col,record,me);
        }
    }, this);

    return Ext.String.format("<ss:Row>{0}</ss:Row>", cells.join(""));
  },

  buildCell: function(value, type, style) {
    if (type == "DateTime" && Ext.isFunction(value.format)) value = value.format(this.dateFormatString);

    return new Ext.ux.exporter.excelFormatter.Cell({
      value: value,
      type : type,
      style: style
    });
  },

  /**
   * @property typeMappings
   * @type Object
   * Mappings from Ext.data.Record types to Excel types
   */
  typeMappings: {
    'int'   : "Number",
    'string': "String",
    'float' : "Number",
    'date'  : "DateTime",


    'TsStringExt':'TsStringExt',
    'TsFloatExt':'TsFloatExt'

  } ,
    /**
     * 对于Ts扩展类型  的映射
     */
    expandTypeMapping:{

        'TsStringExt':'String',
        'TsFloatExt':'String'
    }
});