//    TODO search and select lines for compare
$.Kelude.breadcrumb = {
    showTarget:null,
    unbind_hide_bread: function() {
        $('body').unbind('click.bread');
    },
    bind_hide_bread: function() {
        var that = this;
        $('body').bind('click.bread', function(e) {
            if ( $(e.target).parents().hasClass('switch-bread') || $(e.target).hasClass('switch-bread') ) return;
			if($(e.target).hasClass('ft-select') || $(e.target).parents().hasClass('ft-select')){
				if($("#app-bread-panels").length>0){
					$("#app-bread-panels .switch-bread").hide();
				}
			return;
			}
			if($(e.target).hasClass('more_app')){
				$("#bread-panels .switch-bread").hide()
			return;
			}
            $('.switch-bread').hide();
            that.unbind_hide_bread();
        });
    },
    toggle_bread : function() {
        var that = this;
		var targets=$('#navigation_bread .ft-select');
        targets.live('click', function(e) {
            var $t = $(this);
            var class_name = this.className.replace('ft-select ', '#switch-');
            var nav_width = 95 + 50;
            var o = $t.offset();

            // hide other panel
            if($("#bread-panels div").length==0){
                $.get($(this).children("a").attr("remote_href"),{},function(){
                       that.showTarget = $(class_name);
                       that.showTarget.css({ left:o.left - nav_width,top:28 }).show();
                       that.bind_hide_bread();
                       that.filter_bread();
                },"script");
                return;
            }
            that.showTarget = $(class_name);
            $(class_name).siblings('.switch-bread').each(function(k, v) {
                if ($(v).css('display') == 'block') {
                    $(v).hide();
                    that.unbind_hide_bread();
                }
            });
            //show this panel

            if (that.showTarget.css('display') == 'none') {
                that.showTarget.css({ left:o.left - nav_width,top:28 }).show();
                that.bind_hide_bread();
                that.resize_panel()
            } else {
                that.showTarget.hide();
                that.unbind_hide_bread();
            }
        });
    },
	app_bread : function() {
        var that = this;
		var targets=$("#navigation_bread .more_app");
		if(targets.length<1){
		 return;	
		}
        targets.live('click', function(e) {
            var $t = $(this);
            var class_name ='#switch_'+$(this).parent().attr("id");
            var nav_width = 95 + 50;
            var o = $t.offset();
			var left=o.left;
			var top=o.top;

            // hide other panel
			if($("#app-bread-panels div").length==0){
            $.get($(this).attr("remote_href"),{},function(){
                       that.showTarget = $(class_name);
                       that.showTarget.css({ left:(left - nav_width-80),top:28 }).show();
                       that.bind_hide_bread();
                       that.filter_bread();
             },"script");
			 return;
			}
            that.showTarget = $(class_name);
          	$('#app-bread-panels .switch-bread').each(function(k, v) {
                if ($(v).css('display') == 'block'&& $(v).attr("id")!== that.showTarget.attr("id")) {
                    $(v).hide();
                    that.unbind_hide_bread();
                }
            });
            //show this panel
			
            if (that.showTarget.css('display') == 'none') {
                that.showTarget.css({ left:(left - nav_width-80),top:28 }).show();
                that.bind_hide_bread();
                that.resize_panel()
            } else {
                that.showTarget.hide();
                that.unbind_hide_bread();
            }
        });
    },
    filter_bread : function() {
        var that = this;
        $(".J_bread-search-input").bind('keyup', function(e) {
            if (e.keyCode == 13) {
                if ($(this).val() !== "") {
                    $.getScript($(this).attr('data-search') + "&name=" + encodeURIComponent($(this).val()),function(){
                        that.resize_panel();
                    });
                    that.show_search_resule($(this));
                } else {
                    that.showTarget.find(".pagination a").die("click");
                    that.hide_search_resule($(this));
                }
            }
        })
    },
    hide_search_resule:function($this) {
        var uls = $this.parents(".switch-bread").find('.panel-right ul');
        $(uls[0]).hide();
        $(uls[1]).show();
        this.resize_panel()
    },
    show_search_resule:function($this) {
        var uls = $this.parents(".switch-bread").find('.panel-right ul');
        $(uls[0]).html('<div class="icon_loading">搜索中...</div>');
        $(uls[0]).show();
        $(uls[1]).hide();
        this.resize_panel()
    },
    main_bread:function() {
        this.toggle_bread();
        this.filter_bread();
    },
    die_bread_event:function() {
        $('#navigation_bread .ft-select').die('click');
        $("#navigation_bread .J_bread-search-input").unbind('keyup')
    },
    reply_bread_event:function() {
        //异步加载完后重置面包屑相关事件 author：yelin
        this.die_bread_event();
        this.toggle_bread();
        this.filter_bread();
    },
    resize_panel:function() {
        this.showTarget.find(".panel-list").width(this.showTarget.find(".panel-right").width() + this.showTarget.find(".panel-left").width() + 1);
    }
};
$(document).ready(function() {
    $.Kelude.breadcrumb.main_bread();
	$.Kelude.breadcrumb.app_bread ()
});