/**
 * Copyright (C) 2010-2012 Graham Breach
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * TagCanvas 1.19
 * For more information, please contact <graham@goat1000.com>
 */
(function(){
var i, j, abs = Math.abs, sin = Math.sin, cos = Math.cos, max = Math.max, min = Math.min, 
  hexlookup3 = {}, hexlookup2 = {}, hexlookup1 = {
  0:"0,",   1:"17,",  2:"34,",  3:"51,",  4:"68,",  5:"85,",
  6:"102,", 7:"119,", 8:"136,", 9:"153,", a:"170,", A:"170,",
  b:"187,", B:"187,", c:"204,", C:"204,", d:"221,", D:"221,",
  e:"238,", E:"238,", f:"255,", F:"255,"  
}, Oproto, Tproto, TCproto, doc = document, ocanvas, handlers = {};
for(i = 0; i < 256; ++i) {
  j = i.toString(16);
  if(i < 16)
    j = '0' + j;
  hexlookup2[j] = hexlookup2[j.toUpperCase()] = i.toString() + ',';
}
function Defined(d) {
  return typeof(d) != 'undefined';
}
function Clamp(v, mn, mx) {
  return isNaN(v) ? mx : min(mx, max(mn, v));
}
function Nop() {
  return false;
}
function Shuffle(a) {
  var i = a.length-1, t, p;
  while(i) {
    p = ~~(Math.random()*i);
    t = a[i];
    a[i] = a[p];
    a[p] = t;
    --i;
  }
}
function PointsOnSphere(n,xr,yr,zr) {
  var i, y, r, phi, pts = [], inc = Math.PI * (3-Math.sqrt(5)), off = 2/n;
  for(i = 0; i < n; ++i) {
    y = i * off - 1 + (off / 2);
    r = Math.sqrt(1 - y*y);
    phi = i * inc;
    pts.push([cos(phi) * r * xr, y * yr, sin(phi) * r * zr]);
  }
  return pts;
}
function Cylinder(n,o,xr,yr,zr) {
  var phi, pts = [], inc = Math.PI * (3-Math.sqrt(5)), off = 2/n, i, j, k, l;
  for(i = 0; i < n; ++i) {
    j = i * off - 1 + (off / 2);
    phi = i * inc;
    k = cos(phi);
    l = sin(phi);
    pts.push(o ? [j * xr, k * yr, l * zr] : [k * xr, j * yr, l * zr]);
  }
  return pts;
}
function Ring(o, n, xr, yr, zr, j) {
  var phi, pts = [], inc = Math.PI * 2 / n, i, k, l;
  for(i = 0; i < n; ++i) {
    phi = i * inc;
    k = cos(phi);
    l = sin(phi);
    pts.push(o ? [j * xr, k * yr, l * zr] : [k * xr, j * yr, l * zr]);
  }
  return pts;
}
function PointsOnCylinderV(n,xr,yr,zr) { return Cylinder(n, 0, xr, yr, zr) }
function PointsOnCylinderH(n,xr,yr,zr) { return Cylinder(n, 1, xr, yr, zr) }
function PointsOnRingV(n, xr, yr, zr, offset) {
  offset = isNaN(offset) ? 0 : offset * 1;
  return Ring(0, n, xr, yr, zr, offset);
}
function PointsOnRingH(n, xr, yr, zr, offset) {
  offset = isNaN(offset) ? 0 : offset * 1;
  return Ring(1, n, xr, yr, zr, offset);
}
function SetAlpha(c,a) {
  var d = c, p1, p2, ae = (a*1).toPrecision(3) + ')';
  if(c[0] === '#') {
    if(!hexlookup3[c])
      if(c.length === 4)
        hexlookup3[c] = 'rgba(' + hexlookup1[c[1]] + hexlookup1[c[2]] + hexlookup1[c[3]];
      else
        hexlookup3[c] = 'rgba(' + hexlookup2[c.substr(1,2)] + hexlookup2[c.substr(3,2)] + hexlookup2[c.substr(5,2)];
    d = hexlookup3[c] + ae;
  } else if(c.substr(0,4) === 'rgb(' || c.substr(0,4) === 'hsl(') {
    d = (c.replace('(','a(').replace(')', ',' + ae));
  } else if(c.substr(0,5) === 'rgba(' || c.substr(0,5) === 'hsla(') {
    p1 = c.lastIndexOf(',') + 1, p2 = c.indexOf(')');
    a *= parseFloat(c.substring(p1,p2));
    d = c.substr(0,p1) + a.toPrecision(3) + ')';
  }
  return d;
}
function NewCanvas(w,h) {
  // if using excanvas, give up now
  if(window.G_vmlCanvasManager)
    return null;
  var c = doc.createElement('canvas');
  c.width = w;
  c.height = h;
  return c;
}
// I think all browsers pass this test now...
function ShadowAlphaBroken() {
  var cv = NewCanvas(3,3), c, i;
  if(!cv)
    return false;
  c = cv.getContext('2d');
  c.strokeStyle = '#000';
  c.shadowColor = '#fff';
  c.shadowBlur = 3;
  c.globalAlpha = 0;
  c.strokeRect(2,2,2,2);
  c.globalAlpha = 1;
  i = c.getImageData(2,2,1,1);
  cv = null;
  return (i.data[0] > 0);
}
function FindGradientColour(t,p) {
  var l = 1024, g = t.weightGradient, cv, c, i, gd, d;
  if(t.gCanvas) {
    c = t.gCanvas.getContext('2d');
  } else {
    t.gCanvas = cv = NewCanvas(l,1);
    if(!cv)
      return null;
    c = cv.getContext('2d');
    gd = c.createLinearGradient(0,0,l,0);
    for(i in g)
      gd.addColorStop(1-i, g[i]);
    c.fillStyle = gd;
    c.fillRect(0,0,l,1);
  }
  d = c.getImageData(~~((l-1)*p),0,1,1).data;
  return 'rgba(' + d[0] + ',' + d[1] + ',' + d[2] + ',' + (d[3]/255) + ')';
}
function TextSet(c,f,l,s,sc,sb,so) {
  var xo = (sb || 0) + (so && so[0] < 0 ? abs(so[0]) : 0),
    yo = (sb || 0) + (so && so[1] < 0 ? abs(so[1]) : 0);
  c.font = f;
  c.textBaseline = 'top';
  c.fillStyle = l;
  sc && (c.shadowColor = sc);
  sb && (c.shadowBlur = sb);
  so && (c.shadowOffsetX = so[0], c.shadowOffsetY = so[1]);
  c.fillText(s, xo, yo);
}
function TextToCanvas(s,f,ht,w,h,l,sc,sb,so,padx,pady) {
  var cw = w + abs(so[0]) + sb + sb, ch = h + abs(so[1]) + sb + sb, cv, c;
  cv = NewCanvas(cw+padx,ch+pady);
  if(!cv)
    return null;
  c = cv.getContext('2d');
  TextSet(c,f,l,s,sc,sb,so);
  return cv;
}
function AddShadowToImage(i,sc,sb,so) {
  var sw = abs(so[0]), sh = abs(so[1]),
    cw = i.width + (sw > sb ? sw + sb : sb * 2),
    ch = i.height + (sh > sb ? sh + sb : sb * 2),
    xo = (sb || 0) + (so[0] < 0 ? sw : 0),
    yo = (sb || 0) + (so[1] < 0 ? sh : 0), cv, c;
  cv = NewCanvas(cw, ch);
  if(!cv)
    return null;
  c = cv.getContext('2d');
  sc && (c.shadowColor = sc);
  sb && (c.shadowBlur = sb);
  so && (c.shadowOffsetX = so[0], c.shadowOffsetY = so[1]);
  c.drawImage(i, xo, yo, i.width, i.height);
  return cv;
}
function FindTextBoundingBox(s,f,ht) {
  var w = parseInt(s.length * ht), h = parseInt(ht * 2), cv = NewCanvas(w,h),
    c, idata, w1, h1, x, y, i, ex;
  if(!cv)
    return null;
  c = cv.getContext('2d');
  c.fillStyle = '#000';
  c.fillRect(0,0,w,h);
  TextSet(c,ht + 'px ' + f,'#fff',s)

  idata = c.getImageData(0,0,w,h);
  w1 = idata.width; h1 = idata.height;
  ex = {
    min: { x: w1, y: h1 },
    max: { x: -1, y: -1 }
  };
  for(y = 0; y < h1; ++y) {
    for(x = 0; x < w1; ++x) {
      i = (y * w1 + x) * 4;
      if(idata.data[i+1] > 0) {
        if(x < ex.min.x) ex.min.x = x;
        if(x > ex.max.x) ex.max.x = x;
        if(y < ex.min.y) ex.min.y = y;
        if(y > ex.max.y) ex.max.y = y;
      }
    }
  }
  // device pixels might not be css pixels
  if(w1 != w) {
    ex.min.x *= (w / w1);
    ex.max.x *= (w / w1);
  }
  if(h1 != h) {
    ex.min.y *= (w / h1);
    ex.max.y *= (w / h1);
  }

  cv = null;
  return ex;
}
function FixFont(f) {
  return "'" + f.replace(/(\'|\")/g,'').replace(/\s*,\s*/g, "', '") + "'";
}
function AddHandler(h,f,e) {
  e = e || doc;
  if(e.addEventListener)
    e.addEventListener(h,f,false);
  else
    e.attachEvent('on' + h, f);
}
function AddImage(i,o,t,tc) {
  var tl = tc.taglist, s = tc.imageScale, ic;
  if(s && !(o.width && o.height)) {
    // images are not yet rendered, wait for window onload
    AddHandler('load', function() { AddImage(i,o,t,tc); }, window);
    return;
  }
  if(!i.complete) {
    // image not loaded, wait for image onload
    AddHandler('load',function() { AddImage(i,o,t,tc); }, i);
    return;
  }

  // Yes, this does look like nonsense, but it makes sure that both the
  // width and height are actually set and not just calculated. This is
  // required to keep proportional sizes when the images are hidden, so
  // the images can be used again for another cloud.
  o.width = o.width;
  o.height = o.height;

  if(s) {
    i.width = o.width * s;
    i.height = o.height * s;
  }
  t.w = i.width;
  t.h = i.height;
  if(tc.txtOpt && tc.shadow) {
    ic = AddShadowToImage(i, tc.shadow, tc.shadowBlur, tc.shadowOffset);
    if(ic) {
      t.image = ic;
      t.w = ic.width;
      t.h = ic.height;
    }
  }
  tl.push(t);
}
function GetProperty(e,p) {
  var dv = doc.defaultView, pc = p.replace(/\-([a-z])/g,function(a){return a.charAt(1).toUpperCase()});
  return (dv && dv.getComputedStyle && dv.getComputedStyle(e,null).getPropertyValue(p)) ||
    (e.currentStyle && e.currentStyle[pc]);
}
function FindWeight(t,a) {
  var w = 1, p;
  if(t.weightFrom) {
    w = 1 * (a.getAttribute(t.weightFrom) || t.textHeight);
  } else if(p = GetProperty(a,'font-size')) {
    w = (p.indexOf('px') > -1 && p.replace('px','') * 1) ||
      (p.indexOf('pt') > -1 && p.replace('pt','') * 1.25) ||
      p * 3.3;
  } else {
    t.weight = false;
  }
  return w;
}
function EventToCanvasId(e) {
  return e.target && Defined(e.target.id) ? e.target.id :
    e.srcElement.parentNode.id;
}
function EventXY(e, id) {
  if(Defined(e.offsetX)) {
    return {x: e.offsetX, y: e.offsetY};
  } else {
    var p = AbsPos(id);
    if(Defined(e.changedTouches))
      e = e.changedTouches[0];
    if(e.pageX)
      return {x: e.pageX - p.x, y: e.pageY - p.y};
  }
  return null;
}
function MouseOut(e) {
  var cv = e.target || e.fromElement.parentNode, tc = TagCanvas.tc[cv.id];
  if(tc) {
   tc.mx = tc.my = -1;
   tc.UnFreeze();
   tc.EndDrag();
  }
}
function MouseMove(e) {
  var i, t = TagCanvas, tc, p, tg = EventToCanvasId(e);
  for(i in t.tc) {
    tc = t.tc[i];
    if(tc.tttimer) {
      clearTimeout(tc.tttimer);
      tc.tttimer = null;
    }
  }
  if(tg && t.tc[tg]) {
    tc = t.tc[tg];
    if(p = EventXY(e, tg)) {
      tc.mx = p.x;
      tc.my = p.y;
      tc.Drag(e, p);
    }
    tc.drawn = 0;
  }
}
function MouseDown(e) {
  var t = TagCanvas, cb = doc.addEventListener ? 0 : 1,
    tg = EventToCanvasId(e);
  if(tg && e.button == cb && t.tc[tg]) {
    t.tc[tg].BeginDrag(e);
  }
}
function MouseUp(e) {
  var t = TagCanvas, cb = doc.addEventListener ? 0 : 1,
    tg = EventToCanvasId(e), tc;
  if(tg && e.button == cb && t.tc[tg]) {
    tc = t.tc[tg];
    MouseMove(e);
    if(!tc.EndDrag() && !tc.touched)
      tc.Clicked(e);
  }
}
function TouchDown(e) {
  var t = TagCanvas, tg = EventToCanvasId(e);
  if(tg && e.changedTouches && t.tc[tg]) {
    t.tc[tg].touched = 1;
    t.tc[tg].BeginDrag(e);
  }
}
function TouchUp(e) {
  var t = TagCanvas, tg = EventToCanvasId(e);
  if(tg && e.changedTouches && t.tc[tg]) {
    TouchMove(e);
    if(!t.tc[tg].EndDrag()){
      t.tc[tg].Draw();
      t.tc[tg].Clicked(e);
    }
  }
}
function TouchMove(e) {
  var i, t = TagCanvas, tc, p, tg = EventToCanvasId(e);
  for(i in t.tc) {
    tc = t.tc[i];
    if(tc.tttimer) {
      clearTimeout(tc.tttimer);
      tc.tttimer = null;
    }
  }
  if(tg && t.tc[tg] && e.changedTouches) {
    tc = t.tc[tg];
    if(p = EventXY(e, tg)) {
      tc.mx = p.x;
      tc.my = p.y;
      tc.Drag(e, p);
    }
    tc.drawn = 0;
  }
}
function MouseWheel(e) {
  var t = TagCanvas, tg = EventToCanvasId(e);
  if(tg && t.tc[tg]) {
    e.cancelBubble = true;
    e.returnValue = false;
    e.preventDefault && e.preventDefault();
    t.tc[tg].Wheel((e.wheelDelta || e.detail) > 0);
  }
}
function DrawCanvas() {
  var t = TagCanvas.tc, i;
  for(i in t)
    t[i].Draw();
}
function AbsPos(id) {
  var e = doc.getElementById(id), r = e.getBoundingClientRect(),
    dd = doc.documentElement, b = doc.body, w = window,
    xs = w.pageXOffset || dd.scrollLeft,
    ys = w.pageYOffset || dd.scrollTop,
    xo = dd.clientLeft || b.clientLeft,
    yo = dd.clientTop || b.clientTop;
  return {x:r.left + xs - xo, y:r.top + ys - yo};
}
function RotX(p1,t) {
  var s = sin(t), c = cos(t); 
  return {x:p1.x, y:(p1.y * c) + (p1.z * s), z:(p1.y * -s) + (p1.z * c)};
}
function RotY(p1,t) {
  var s = sin(t), c = cos(t); 
  return {x:(p1.x * c) + (p1.z * -s), y:p1.y, z:(p1.x * s) + (p1.z * c)};
}
function Project(tc,p1,w,h,sx,sy) {
  var yn, xn, zn, m = tc.z1 / (tc.z1 + tc.z2 + p1.z);
  yn = p1.y * m * sy;
  xn = p1.x * m * sx;
  zn = tc.z2 + p1.z;
  return {x:xn, y:yn, z:zn};
}
/**
 * @constructor
 */
function Outline(tc) {
  this.ts = new Date().valueOf();
  this.tc = tc;
  this.x = this.y = this.w = this.h = this.sc = 1;
  this.z = 0;
  this.Draw = tc.pulsateTo < 1 && tc.outlineMethod != 'colour' ? this.DrawPulsate : this.DrawSimple;
  this.SetMethod(tc.outlineMethod);
}
Oproto = Outline.prototype;
Oproto.SetMethod = function(om) {
  var methods = {
    block: ['PreDraw','DrawBlock'],
    colour: ['PreDraw','DrawColour'],
    outline: ['PostDraw','DrawOutline'],
    classic: ['LastDraw','DrawOutline'],
    none: ['LastDraw']
  }, funcs = methods[om] || methods.outline;
  if(om == 'none') {
    this.Draw = function() { return 1; }
  } else {
    this.drawFunc = this[funcs[1]];
  }
  this[funcs[0]] = this.Draw;
};
Oproto.Update = function(x,y,w,h,sc,p,xo,yo) {
  var o = this.tc.outlineOffset, o2 = 2 * o;
  this.x = sc * x + xo - o;
  this.y = sc * y + yo - o;
  this.w = sc * w + o2;
  this.h = sc * h + o2;
  this.sc = sc; // used to determine frontmost
  this.z = p.z;
};
Oproto.DrawOutline = function(c,x,y,w,h,colour) {
  c.strokeStyle = colour;
  c.strokeRect(x,y,w,h);
};
Oproto.DrawColour = function(c,x,y,w,h,colour,tag,x1,y1) {
  return this[tag.image ? 'DrawColourImage' : 'DrawColourText'](c,x,y,w,h,colour,tag,x1,y1);
};
Oproto.DrawColourText = function(c,x,y,w,h,colour,tag,x1,y1) {
  var normal = tag.colour;
  tag.colour = colour;
  tag.alpha = 1;
  tag.Draw(c,x1,y1);
  tag.colour = normal;
  return 1;
};
Oproto.DrawColourImage = function(c,x,y,w,h,colour,tag,x1,y1) {
  var ccanvas = c.canvas, fx = ~~max(x,0), fy = ~~max(y,0), 
    fw = min(ccanvas.width - fx, w) + .5|0, fh = min(ccanvas.height - fy,h) + .5|0, cc;
  if(ocanvas)
    ocanvas.width = fw, ocanvas.height = fh;
  else
    ocanvas = NewCanvas(fw, fh);
  if(!ocanvas)
    return this.SetMethod('outline'); // if using IE and images, give up!
  cc = ocanvas.getContext('2d');

  cc.drawImage(ccanvas,fx,fy,fw,fh,0,0,fw,fh);
  c.clearRect(fx,fy,fw,fh);
  tag.alpha = 1;
  tag.Draw(c,x1,y1);
  c.setTransform(1,0,0,1,0,0);
  c.save();
  c.beginPath();
  c.rect(fx,fy,fw,fh);
  c.clip();
  c.globalCompositeOperation = 'source-in';
  c.fillStyle = colour;
  c.fillRect(fx,fy,fw,fh);
  c.restore();
  c.globalCompositeOperation = 'destination-over';
  c.drawImage(ocanvas,0,0,fw,fh,fx,fy,fw,fh);
  c.globalCompositeOperation = 'source-over';
  return 1;
};
Oproto.DrawBlock = function(c,x,y,w,h,colour) {
  c.fillStyle = colour;
  c.fillRect(x,y,w,h);
};
Oproto.DrawSimple = function(c, tag, x1, y1) {
  var t = this.tc;
  c.setTransform(1,0,0,1,0,0);
  c.strokeStyle = t.outlineColour;
  c.lineWidth = t.outlineThickness;
  c.shadowBlur = c.shadowOffsetX = c.shadowOffsetY = 0;
  c.globalAlpha = 1;
  return this.drawFunc(c,this.x,this.y,this.w,this.h,t.outlineColour,tag,x1,y1);
};
Oproto.DrawPulsate = function(c, tag, x1, y1) {
  var diff = new Date().valueOf() - this.ts, t = this.tc;
  c.setTransform(1,0,0,1,0,0);
  c.strokeStyle = t.outlineColour;
  c.lineWidth = t.outlineThickness;
  c.shadowBlur = c.shadowOffsetX = c.shadowOffsetY = 0;
  c.globalAlpha = t.pulsateTo + ((1 - t.pulsateTo) * 
    (0.5 + (cos(2 * Math.PI * diff / (1000 * t.pulsateTime)) / 2)));
  return this.drawFunc(c,this.x,this.y,this.w,this.h,t.outlineColour,tag,x1,y1);
};
Oproto.Active = function(c,x,y) {
  return (x >= this.x && y >= this.y &&
    x <= this.x + this.w && y <= this.y + this.h);
};
Oproto.PreDraw = Oproto.PostDraw = Oproto.LastDraw = Nop;
/**
 * @constructor
 */
function Tag(tc,name,a,v,w,h,col,font) {
  var c = tc.ctxt;
  this.tc = tc;
  this.image = name.src ? name : null;
  this.name = name.src ? '' : name;
  this.title = a.title || null;
  this.a = a;
  this.p3d = { x: v[0] * tc.radius, y: v[1] * tc.radius, z: v[2] * tc.radius };
  this.x = this.y = 0;
  this.w = w;
  this.h = h;
  this.colour = col || tc.textColour;
  this.textFont = font || tc.textFont;
  this.weight = this.sc = this.alpha = 1;
  this.weighted = !tc.weight;
  this.outline = new Outline(tc);
  if(!this.image) {
    this.textHeight = tc.textHeight;
    this.extents = FindTextBoundingBox(this.name, this.textFont, this.textHeight);
    this.Measure(c,tc);
  }
  this.SetShadowColour = tc.shadowAlpha ? this.SetShadowColourAlpha : this.SetShadowColourFixed;
  this.SetDraw(tc);
}
Tproto = Tag.prototype;
Tproto.SetDraw = function(t) {
  this.Draw = this.image ? (t.ie > 7 ? this.DrawImageIE : this.DrawImage) : this.DrawText;
  t.noSelect && (this.CheckActive = Nop);
};
Tproto.Measure = function(c,t) {
  this.h = this.extents ? this.extents.max.y + this.extents.min.y : this.textHeight;
  c.font = this.font = this.textHeight + 'px ' + this.textFont;
  this.w = c.measureText(this.name).width;
  if(t.txtOpt) {
    var s = t.txtScale, th = s * this.textHeight, f = th + 'px ' + this.textFont,
      soff = [s*t.shadowOffset[0],s*t.shadowOffset[1]], cw;
    c.font = f;
    cw = c.measureText(this.name).width;
    this.image = TextToCanvas(this.name, f, th, cw, s * this.h, this.colour,
      t.shadow, s * t.shadowBlur, soff, s, s);
    if(this.image) {
      this.w = this.image.width / s;
      this.h = this.image.height / s;
    }
    this.SetDraw(t);
    t.txtOpt = this.image;
  }
};
Tproto.SetWeight = function(w) {
  if(!this.name.length)
    return;
  this.weight = w;
  this.Weight(this.tc.ctxt, this.tc);
  this.Measure(this.tc.ctxt, this.tc);
};
Tproto.Weight = function(c,t) {
  var w = this.weight, m = t.weightMode;
  this.weighted = true;
  if(m == 'colour' || m == 'both')
    this.colour = FindGradientColour(t, (w - t.min_weight) / (t.max_weight-t.min_weight));
  if(m == 'size' || m == 'both')
    this.textHeight = w * t.weightSize;
  this.extents = FindTextBoundingBox(this.name, this.textFont, this.textHeight);
};
Tproto.SetShadowColourFixed = function(c,s,a) {
  c.shadowColor = s;
};
Tproto.SetShadowColourAlpha = function(c,s,a) {
  c.shadowColor = SetAlpha(s, a);
};
Tproto.DrawText = function(c,xoff,yoff) {
  var t = this.tc, x = this.x, y = this.y, s = this.sc;
  c.globalAlpha = this.alpha;
  c.fillStyle = this.colour;
  t.shadow && this.SetShadowColour(c,t.shadow,this.alpha);
  c.font = this.font;
  x += (xoff / s) - (this.w / 2);
  y += (yoff / s) - (this.h / 2);
  c.setTransform(s, 0, 0, s, s * x, s * y);
  c.fillText(this.name, 0, 0);
};
Tproto.DrawImage = function(c,xoff,yoff) {
  var x = this.x, y = this.y, s = this.sc,
    i = this.image, w = this.w, h = this.h, a = this.alpha,
    shadow = this.shadow;
  c.globalAlpha = a;
  c.fillStyle = this.colour;
  shadow && this.SetShadowColour(c,shadow,a);
  x += (xoff / s) - (w / 2);
  y += (yoff / s) - (h / 2);
  c.setTransform(s, 0, 0, s, s * x, s * y);
  c.drawImage(i, 0, 0, w, h);
};
Tproto.DrawImageIE = function(c,xoff,yoff) {
  var i = this.image, s = this.sc,
    w = i.width = this.w*s, h = i.height = this.h * s,
    x = (this.x*s) + xoff - (w/2), y = (this.y*s) + yoff - (h/2);
  c.setTransform(1,0,0,1,0,0);
  c.globalAlpha = this.alpha;
  c.drawImage(i, x, y);
};
Tproto.Calc = function(yaw,pitch) {
  var pp = RotY(this.p3d, yaw), t = this.tc, mnb = t.minBrightness,
    mxb = t.maxBrightness, r = t.max_radius;
  this.p3d = RotX(pp, pitch);
  pp = Project(t, this.p3d, this.w, this.h, t.stretchX, t.stretchY);
  this.x = pp.x;
  this.y = pp.y;
  this.sc = (t.z1 + t.z2 - pp.z) / t.z2;
  this.alpha = Clamp(mnb + (mxb - mnb) * (r - this.p3d.z) / (2 * r), 0, 1);
};
Tproto.CheckActive = function(c,xoff,yoff) {
  var t = this.tc, o = this.outline,
    w = this.w, h = this.h,
    x = this.x - w/2, y = this.y - h/2;
  o.Update(x, y, w, h, this.sc, this.p3d, xoff, yoff);
  return o.Active(c, t.mx, t.my) ? o : null;
};
Tproto.Clicked = function(e) {
  var a = this.a, t = a.target, h = a.href, evt;
  if(t != '' && t != '_self') {
    if(self.frames[t]) {
      self.frames[t] = h;
    } else{
      try {
        if(top.frames[t]) {
          top.frames[t] = h;
          return;
        }
      } catch(err) {
        // different domain/port/protocol?
      }
      window.open(h, t);
    }
    return;
  }
  if(doc.createEvent) {
    evt = doc.createEvent('MouseEvents');
    evt.initMouseEvent('click', 1, 1, window, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null);
    if(!a.dispatchEvent(evt))
      return;
  } else if(a.fireEvent) {
    if(!a.fireEvent('onclick'))
      return;
  }
};
/**
 * @constructor
 */
function TagCanvas(cid,lctr,opt) {
  var i, ctr, tl, vl, p, im, ii, tag, c = doc.getElementById(cid), 
    cp = ['id','class','innerHTML'], w, weights = [], shape, shapeArgs,
    pfuncs = {
      sphere: PointsOnSphere,
      vcylinder: PointsOnCylinderV,
      hcylinder: PointsOnCylinderH,
      vring: PointsOnRingV,
      hring: PointsOnRingH
    };

  if(!c) throw 0;
  if(Defined(window.G_vmlCanvasManager)) {
    c = window.G_vmlCanvasManager.initElement(c);
    this.ie = parseFloat(navigator.appVersion.split('MSIE')[1]);
  }
  if(c && (!c.getContext || !c.getContext('2d').fillText)) {
    p = doc.createElement('DIV');
    for(i = 0; i < cp.length; ++i)
      p[cp[i]] = c[cp[i]];
    c.parentNode.insertBefore(p,c);
    c.parentNode.removeChild(c);
    throw 0;
  }
  for(i in TagCanvas.options)
    this[i] = opt && Defined(opt[i]) ? opt[i] : 
      (Defined(TagCanvas[i]) ? TagCanvas[i] : TagCanvas.options[i]);

  this.canvas = c;
  this.ctxt = c.getContext('2d');
  this.z1 = (19800 / (Math.exp(this.depth) * (1-1/Math.E))) +
    20000 - 19800 / (1-1/Math.E);
  this.z2 = this.z1 / this.zoom;
  this.radius = min(c.height, c.width) * 0.37 * (this.z2 + this.z1) / this.z1;
  this.max_radius = this.radius * max(this.radiusX,
    max(this.radiusY, this.radiusZ));
  this.max_weight = 0;
  this.min_weight = 200;
  this.textFont = this.textFont && FixFont(this.textFont);
  this.textHeight *= 1;
  this.pulsateTo = Clamp(this.pulsateTo, 0, 1);
  this.minBrightness = Clamp(this.minBrightness, 0, 1);
  this.maxBrightness = Clamp(this.maxBrightness, this.minBrightness, 1);
  this.ctxt.textBaseline = 'top';
  this.lx = (this.lock + '').indexOf('x') + 1;
  this.ly = (this.lock + '').indexOf('y') + 1;
  this.frozen = 0;
  this.dx = this.dy = 0;
  this.touched = 0;
  this.Animate = this.dragControl ? this.AnimateDrag : this.AnimatePosition;
  if(this.shadowBlur || this.shadowOffset[0] || this.shadowOffset[1]) {
    // let the browser translate "red" into "#ff0000"
    this.ctxt.shadowColor = this.shadow;
    this.shadow = this.ctxt.shadowColor;
    this.shadowAlpha = ShadowAlphaBroken();
  } else {
    delete this.shadow;
  }
  try {
    ctr = doc.getElementById(lctr || cid);
    tl = ctr.getElementsByTagName('a');
    this.taglist = [];

    if(tl.length) {
      shapeArgs = this.shape.toString().split(/[(),]/);
      shape = shapeArgs.shift();
      this.shape = pfuncs[shape] || pfuncs.sphere;
      shapeArgs = [tl.length, this.radiusX, this.radiusY, this.radiusZ].concat(shapeArgs);
      vl = this.shape.apply(this, shapeArgs);
      this.shuffleTags && Shuffle(vl);
      this.listLength = tl.length;
      for(i = 0; i < tl.length; ++i) {
        im = tl[i].getElementsByTagName('img');
        if(im.length) {
          ii = new Image;
          ii.src = im[0].src;
          tag = new Tag(this, ii, tl[i], vl[i], 1, 1);
          AddImage(ii,im[0],tag,this);
        } else {
          this.taglist.push(new Tag(this, tl[i].innerText || tl[i].textContent, tl[i],
            vl[i], 2, this.textHeight + 2, this.textColour || GetProperty(tl[i],'color'),
            this.textFont || FixFont(GetProperty(tl[i],'font-family'))));
        }
        if(this.weight) {
          w = FindWeight(this,tl[i]);
          if(w > this.max_weight) this.max_weight = w;
          if(w < this.min_weight) this.min_weight = w;
          weights.push(w);
        }
      }
      if(this.weight = (this.max_weight > this.min_weight)) {
        for(i = 0; i < this.taglist.length; ++i) {
          this.taglist[i].SetWeight(weights[i]);
        }
      }
    }
    if(lctr && this.hideTags) {
      if(TagCanvas.loaded)
        ctr.style.display = 'none';
      else
        AddHandler('load', function() { ctr.style.display = 'none'; }, window);
    }
  } catch(ex) {
    ex;
  }

  this.yaw = this.initial ? this.initial[0] * this.maxSpeed : 0;
  this.pitch = this.initial ? this.initial[1] * this.maxSpeed : 0;
  if(this.tooltip) {
    if(this.tooltip == 'native') {
      this.Tooltip = this.TooltipNative;
    } else {
      this.Tooltip = this.TooltipDiv;
      if(!this.ttdiv) {
        this.ttdiv = doc.createElement('div');
        this.ttdiv.className = this.tooltipClass;
        this.ttdiv.style.position = 'absolute';
        this.ttdiv.style.zIndex = c.style.zIndex + 1;
        AddHandler('mouseover',function(e){e.target.style.display='none';},this.ttdiv);
        doc.body.appendChild(this.ttdiv);
      }
    }
  } else {
    this.Tooltip = this.TooltipNone;
  }
  if(!this.noMouse && !handlers[cid]) {
    AddHandler('mousemove', MouseMove, c);
    AddHandler('mouseout', MouseOut, c);
    AddHandler('mouseup', MouseUp, c);
    if(this.dragControl) {
      AddHandler('mousedown', MouseDown, c);
      AddHandler('touchstart', TouchDown, c);
      AddHandler('touchend', TouchUp, c);
      AddHandler('touchcancel', TouchUp, c);
      AddHandler('touchmove', TouchMove, c);
      AddHandler('selectstart', Nop, c);
    }
    if(this.wheelZoom) {
      AddHandler('mousewheel', MouseWheel, c);
      AddHandler('DOMMouseScroll', MouseWheel, c);
    }
    handlers[cid] = 1;
  }
  TagCanvas.started || (TagCanvas.started = setInterval(DrawCanvas, this.interval));
}
TCproto = TagCanvas.prototype;
TCproto.Draw = function() {
  if(this.paused)
    return;
  var cv = this.canvas, cw = cv.width, ch = cv.height, max_sc = 0,
    yaw = this.yaw, pitch = this.pitch,
    x = cw / 2 + this.offsetX, y = ch / 2 + this.offsetY, c = this.ctxt,
    active, a, i, aindex = -1, tl = this.taglist, l = tl.length,
    frontsel = this.frontSelect, centreDrawn = (this.centreFunc == Nop);
  if(this.frozen && this.drawn)
    return this.Animate(cw,ch);
  c.setTransform(1,0,0,1,0,0);
  this.active = null;
  for(i = 0; i < l; ++i)
    tl[i].Calc(yaw, pitch);
  tl = tl.sort(function(a,b) {return b.p3d.z-a.p3d.z});
  
  for(i = 0; i < l; ++i) {
    a = this.mx >= 0 && this.my >= 0 && tl[i].CheckActive(c, x, y);
    if(a && a.sc > max_sc && (!frontsel || a.z <= 0)) {
      active = a;
      active.index = aindex = i;
      max_sc = a.sc;
    }
  }
  this.active = active;

  if(!this.txtOpt && this.shadow) {
    c.shadowBlur = this.shadowBlur;
    c.shadowOffsetX = this.shadowOffset[0];
    c.shadowOffsetY = this.shadowOffset[1];
  }
  c.clearRect(0,0,cw,ch);
  for(i = 0; i < l; ++i) {
    if(!centreDrawn && tl[i].p3d.z <= 0) {
      // run the centreFunc if the next tag is at the front
      try { this.centreFunc(c, cw, ch, x, y); }
      catch(e) {
        alert(e);
        // don't run it again
        this.centreFunc = Nop;
      }
      centreDrawn = true;
    }

    if(!(aindex == i && active.PreDraw(c, tl[i], x, y)))
      tl[i].Draw(c, x, y);
    aindex == i && active.PostDraw(c);
  }
  if(this.freezeActive && active) {
    this.Freeze();
  } else {
    this.UnFreeze();
    this.drawn = (l == this.listLength);
  }
  this.Animate(cw, ch);
  active && active.LastDraw(c);
  cv.style.cursor = active ? this.activeCursor : '';
  this.Tooltip(active,tl[aindex]);
};
TCproto.TooltipNone = function() { };
TCproto.TooltipNative = function(active,tag) {
  this.canvas.title = active && tag.title ? tag.title : '';
};
TCproto.TooltipDiv = function(active,tag) {
  var tc = this, s = tc.ttdiv.style, cid = tc.canvas.id, none = 'none';
  if(active && tag.title) {
    if(tag.title != tc.ttdiv.innerHTML)
      s.display = none;
    tc.ttdiv.innerHTML = tag.title;
    tag.title = tc.ttdiv.innerHTML;
    if(s.display == none && ! tc.tttimer) {
      tc.tttimer = setTimeout(function() {
        var p = AbsPos(cid);
        s.display = 'block';
        s.left = p.x + tc.mx + 'px';
        s.top = p.y + tc.my + 24 + 'px';
        tc.tttimer = null;
      }, tc.tooltipDelay);
    }
  } else {
    s.display = none;
  }
};
TCproto.AnimatePosition = function(w, h) {
  var tc = this, x = tc.mx, y = tc.my, s, r;
  if(!tc.frozen && x >= 0 && y >= 0 && x < w && y < h) {
    s = tc.maxSpeed, r = tc.reverse ? -1 : 1;
    tc.lx || (tc.yaw = r * ((s * 2 * x / w) - s));
    tc.ly || (tc.pitch = r * -((s * 2 * y / h) - s));
    tc.initial = null;
  } else if(!tc.initial) {
    if(tc.frozen && !tc.freezeDecel)
      tc.yaw = tc.pitch = 0;
    else
      tc.Decel(tc);
  }
};
TCproto.AnimateDrag = function(w, h) {
  var tc = this, rs = 100 * tc.maxSpeed / tc.max_radius / tc.zoom;
  if(tc.dx || tc.dy) {
    tc.lx || (tc.yaw = tc.dx * rs / tc.stretchX);
    tc.ly || (tc.pitch = tc.dy * -rs / tc.stretchY);
    tc.dx = tc.dy = 0;
    tc.initial = null;
  } else if(!tc.initial) {
    tc.Decel(tc);
  }
};
TCproto.Freeze = function() {
  if(!this.frozen) {
    this.preFreeze = [this.yaw, this.pitch];
    this.frozen = 1;
    this.drawn = 0;
  }
};
TCproto.UnFreeze = function() {
  if(this.frozen) {
    this.yaw = this.preFreeze[0];
    this.pitch = this.preFreeze[1];
    this.frozen = 0;
  }
};
TCproto.Decel = function(tc) {
  var s = tc.minSpeed, ay = abs(tc.yaw), ap = abs(tc.pitch);
  if(!tc.lx && ay > s)
    tc.yaw = ay > tc.z0 ? tc.yaw * tc.decel : 0;
  if(!tc.ly && ap > s)
    tc.pitch = ap > tc.z0 ? tc.pitch * tc.decel : 0;
};
TCproto.Zoom = function(r) {
  this.z2 = this.z1 * (1/r);
  this.drawn = 0;
};
TCproto.Clicked = function(e) {
  var t = this.taglist, a = this.active;
  try {
    if(a && t[a.index]) 
      t[a.index].Clicked(e);
  } catch(ex) {
  }
};
TCproto.Wheel = function(i) {
  var z = this.zoom + this.zoomStep * (i ? 1 : -1);
  this.zoom = min(this.zoomMax,max(this.zoomMin,z));
  this.Zoom(this.zoom);
};
TCproto.BeginDrag = function(e) {
  this.down = EventXY(e, this.canvas.id);
  e.cancelBubble = true;
  e.returnValue = false;
  e.preventDefault && e.preventDefault();
};
TCproto.Drag = function(e, p) {
  if(this.dragControl && this.down) {
    var t2 = this.dragThreshold * this.dragThreshold,
      dx = p.x - this.down.x, dy = p.y - this.down.y;
    if(this.dragging || dx * dx + dy * dy > t2) {
      this.dx = dx;
      this.dy = dy;
      this.dragging = 1;
      this.down = p;
    }
  }
};
TCproto.EndDrag = function() {
  var res = this.dragging;
  this.dragging = this.down = null;
  return res;
};
TCproto.Pause = function() { this.paused = true; };
TCproto.Resume = function() { this.paused = false; };
TagCanvas.Start = function(id,l,o) {
  TagCanvas.tc[id] = new TagCanvas(id,l,o);
};
TagCanvas.Pause = function(id) {
  TagCanvas.tc[id] && TagCanvas.tc[id].Pause();
};
TagCanvas.Resume = function(id) {
  TagCanvas.tc[id] && TagCanvas.tc[id].Resume();
};
TagCanvas.tc = {};
TagCanvas.options = {
z1: 20000,
z2: 20000,
z0: 0.0002,
freezeActive: false,
freezeDecel: false,
activeCursor: 'pointer',
pulsateTo: 1,
pulsateTime: 3,
reverse: false,
depth: 0.5,
maxSpeed: 0.05,
minSpeed: 0,
decel: 0.95,
interval: 20,
minBrightness: 0.1,
maxBrightness: 1,
outlineColour: '#ffff99',
outlineThickness: 2,
outlineOffset: 5,
outlineMethod: 'outline',
textColour: '#ff99ff',
textHeight: 15,
textFont: 'Helvetica, Arial, sans-serif',
shadow: '#000',
shadowBlur: 0,
shadowOffset: [0,0],
initial: null,
hideTags: true,
zoom: 1,
weight: false,
weightMode: 'size',
weightFrom: null,
weightSize: 1,
weightGradient: {0:'#f00', 0.33:'#ff0', 0.66:'#0f0', 1:'#00f'},
txtOpt: true,
txtScale: 2,
frontSelect: false,
wheelZoom: true,
zoomMin: 0.3,
zoomMax: 3,
zoomStep: 0.05,
shape: 'sphere',
lock: null,
tooltip: null,
tooltipDelay: 300,
tooltipClass: 'tctooltip',
radiusX: 1,
radiusY: 1,
radiusZ: 1,
stretchX: 1,
stretchY: 1,
offsetX: 0,
offsetY: 0,
shuffleTags: false,
noSelect: false,
noMouse: false,
imageScale: 1,
paused: false,
dragControl: false,
dragThreshold: 4,
centreFunc: Nop
};
for(i in TagCanvas.options) TagCanvas[i] = TagCanvas.options[i];
window.TagCanvas = TagCanvas;
// set a flag for when the window has loaded
AddHandler('load',function(){TagCanvas.loaded=1},window);
})();
