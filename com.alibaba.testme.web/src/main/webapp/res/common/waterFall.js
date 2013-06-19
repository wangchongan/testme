(function ($) {
    var container,
        checkFlag = true,
        colArray = [],
        colCount = 4,
        delayer,
        currentPage = 0,
        OPTIONS = {
            gapHeight: 13,
            gapWidth: 13,
            colWidth: 250,
            loadPages: 12,
            loadType: "index",
            keyword: "",
            laodHref: "#",
            scroller: $(window)
        },
        isLastPage = false;

    getMinVal = function (arr) {
        return Math.min.apply(Math, arr);
    },
        getMaxVal = function (arr) {
            return Math.max.apply(Math, arr);
        },
        getMinKey = function (arr) {
            var key = 0, min = arr[0];
            for (var i = 1, len = arr.length; i < len; i++) {
                if (arr[i] < min) {
                    key = i;
                    min = arr[i];
                }
            }
            return key;
        },
        getMaxKey = function (arr) {
            var key = 0, max = arr[0];
            for (var i = 1, len = arr.length; i < len; i++) {
                if (arr[i] > max) {
                    key = i;
                    max = arr[i];
                }
            }
            return key;
        },
        layerCells = function (nodes) {
            var colIndex, colHeight;
            nodes.each(function (i, el) {
                colIndex = getMinKey(colArray);
                colHeight = colArray[colIndex];
                el.style.left = colIndex * (OPTIONS.colWidth + OPTIONS.gapWidth) + OPTIONS.gapWidth + 'px';
                el.style.top = colHeight + 'px';
                colArray[colIndex] = colHeight + OPTIONS.gapHeight + el.offsetHeight;
                el.className = el.className + ' ready';
            });
            container.height(getMaxVal(colArray) + 'px');
            checkFlag = true;
            if (nodes.length < OPTIONS.loadPages) {
                isLastPage = true;
            }
            loadCheck();
            grayscale($('div.inactivate'));
        },
        appendCells = function (num) {
            currentPage += 1;
            $.ajax({
                url: OPTIONS.loadHref,
                data: {
                    page_no: currentPage,
                    cells_per_page: OPTIONS.loadPages,
                    type: OPTIONS.loadType,
                    keyword: OPTIONS.keyword
                },
                method: "post",
                dataType: "html",
                success: function (data) {
                    container.append(data);
                    var cells = $(".cell:not('.ready')");
                    layerCells(cells);
                }
            });
        },
        reflowCheck = function () {
            colCount = Math.floor((document.body.offsetWidth + OPTIONS.gapWidth) / (OPTIONS.colWidth + OPTIONS.gapWidth));
            colCount = colCount < 4 ? colCount : 4;
            if (colArray.length != colCount) {
                colArray = [];
                container.width((colCount * (OPTIONS.colWidth + OPTIONS.gapWidth) - OPTIONS.gapWidth) + 'px');
                for (var i = 0; i < colCount; i++) {
                    colArray.push(OPTIONS.gapHeight);
                }
                layerCells(container.children());
            } else {
                loadCheck();
            }
        },
        loadCheck = function () {
            if (!isLastPage) {
                if (checkFlag && ($(OPTIONS.scroller).height() + $(OPTIONS.scroller)[0].scrollTop) > getMinVal(colArray)) {
                    checkFlag = false;
                    appendCells(colCount);
                }
            }
            else {
                if ($("#nopage").length < 1) {
                    container.append('<div class="nopage" id="nopage">已经最后一页了</div>');
                    container.height(container.height() + 30);
                }
            }
        },
        reflowDelay = function () {
            clearTimeout(delayer);
            delayer = setTimeout(reflowCheck, 500);
        },
        init = function () {
            colCount = Math.floor((document.body.offsetWidth + OPTIONS.gapWidth) / (OPTIONS.colWidth + OPTIONS.gapWidth));
            colCount = colCount < 4 ? colCount : 4;
            container.width((colCount * (OPTIONS.colWidth + OPTIONS.gapWidth) - OPTIONS.gapWidth) + 'px');
            for (var i = 0; i < colCount; i++) {
                colArray.push(OPTIONS.gapHeight);
            }
            loadCheck();
        };
    $.fn.waterFall = function (options) {
        $.extend(OPTIONS, options);
        container = $(this);
        init();
        $(OPTIONS.scroller).scroll(function () {
            loadCheck();
        });
        $(window).resize(function () {
            reflowDelay();
        });
    };
})(jQuery);