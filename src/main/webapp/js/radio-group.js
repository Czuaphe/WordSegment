


Vue.component('radio-group', {
    props: {
        array: {
            type: Array
        }
    },
    template: '<div>' +
    '   <label v-for="name of array" style="margin: 0 10px;">' +
    '       <input type="radio" :name="tempName" :value="name" @change="change"/>' +
    '       <div style="display: inline-block;">' +
    '           <i class="icon-circle-blank"></i>' +
    '       </div>' +
    '       <span>{{name}}</span>' +
    '   </label>' +
    '</div>',
    computed: {
        tempName: function () {
            return "test";
        }
    },
    methods: {
        change: function (ev) {
            this.$emit('radio', ev.target.value);

            // 没有那么简单
            $(ev.target).next().children("i").toggleClass("icon-circle");
            $(ev.target).next().children("i").toggleClass("icon-circle-blank");
        }
    }
});