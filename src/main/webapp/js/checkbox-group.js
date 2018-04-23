/**
 * 需要传入array参数，test参数
 * 事件：checkbox，返回的值：value 改变值的checkbox的value；checked 改变值的checkbox的checked
 *  todo class中的样式还没有定义完
 */
Vue.component('checkbox-group', {
    props: {
        type: {
            type:String,
            required: true
        },
        array:{
            type:Array,
            required: true
        },
        activeWidth: {
            type:Number
        },
        test: {
            type: Boolean,
            default: false
        }},
    created: function() {
        console.log(this.activeWidth);
    },
    template: '<div>' +
    '   <label v-for="(name, index) of names" style="display: inline-block;margin-right: 20px;" >' +
    '       <input v-if="test" :type="type" name="test" :checked="array[name]" :value="name" @change="change">' +
    '       <input v-else style="display: none" :type="type" name="test" :checked="array[name]" :value="name" @change="change">' +
    '       <div v-if="type==\'checkbox\'" style="display: inline-block;width: 15px;">\n' +
    '           <i v-if="array[name]" class="icon-check"></i>' +
    '           <i v-else class="icon-check-empty"></i>' +
    '       </div>' +
    '       <div v-if="type==\'radio\'" style="display: inline-block;">\n' +
    '           <i v-if="array[name]" class="icon-circle"></i>' +
    '           <i v-else class="icon-circle-blank"></i>' +
    '       </div>' +
    '       <span v-if="test" >{{array[name]}}</span>' +
    '       <span v-else>{{name}}</span>' +
    '   </label>' +
    '</div>',
    computed: {
        // 得到所有的checkbox的值
        names: function () {
            // 得到对应的Key
            var names = [];
            for (var name in this.array) {
                names.push(name);
            }
            // console.log(name.length);
            return names;
        }
    },
    methods: {
        change: function (ev) {
            // 解耦，发送checkbox事件，让父组件去更新数据
            this.$emit('change', ev.target.value, ev.target.checked, this.names);
        }
    }
});