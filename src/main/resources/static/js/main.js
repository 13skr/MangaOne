var mangaApi = Vue.resource('/manga{/id}');

Vue.component('manga-row', {
    props: ['manga'],
    template: '<div><i>{{manga.id}}</i> {{manga.name}}</div>'
})

Vue.component('list-manga', {
    props: ['mangas'],
    template:
        '<div>' +
            '<manga-row v-for="manga in mangas" :key="manga.id" :manga="manga"/>' +
        '</div>',
    created: function () {
        mangaApi.get().then(response =>
            response.json().then(data =>
                data.forEach(item => this.mangas.push(item))
            )
        )
    }
});

var app = new Vue({
    el: '#app',
    template: '<list-manga :mangas="mangaList" />',
    data: {
        mangaList: []
    }
});