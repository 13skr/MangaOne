<template>
    <v-app id="sandbox" >
        <v-navigation-drawer v-model="drawer" clipped app>
            <v-list>
                <v-list-item link to="/create">
                    <v-list-item-avatar icon>
                        <v-icon>add</v-icon>
                    </v-list-item-avatar>
                    <v-list-item-content>
                        <v-list-item-title>
                            Create Manga
                        </v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>

        <v-app-bar clipped-left app>
            <v-app-bar-nav-icon @click="drawer = !drawer"/>
            <v-tab to="/">
                <v-toolbar-title>MangaOne</v-toolbar-title>
            </v-tab>
            <v-autocomplete :items="mangaArray" :loading="isLoading"
                            :search-input.sync="search" clearable hide-details
                            hide-selected item-text="name" item-value="symbol"
                            label="Search for manga..." solo>
                <template v-slot:no-data>
                    <v-list-item>
                        <v-list-item-title>
                            Search for
                            <strong>Manga</strong>
                        </v-list-item-title>
                    </v-list-item>
                </template>
                <template v-slot:item="{ item }">
                    <v-list-item @click="linkToManga(item.id)">
                        <v-list-item-avatar color="indigo" class="headline font-weight-light white--text">
                            {{ item.name.charAt(0) }}
                        </v-list-item-avatar>
                        <v-list-item-content>
                            <v-list-item-title v-text="item.name"></v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </template>
            </v-autocomplete>
        </v-app-bar>

        <v-content >
            <v-container fluid>
                <router-view></router-view>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import {mapState} from 'vuex'
    import {mapMutations} from 'vuex'
    import axios from 'axios'

    export default {
        data() {
            return {
                drawer: false,
                isLoading: false,
                search: null,
            }
        },
        computed: {
            ...mapState(['mangaArray']),
        },
        methods: {
            ...mapMutations(['addMangaArrayMutation']),
            linkToManga(index) {
                this.$router.push('/manga/' + index)
            }
        },
        watch: {
            search () {
                const current = this

                if (this.mangaArray.length > 0) return
                this.isLoading = true

                axios.get('/api/manga')
                    .then(response => {
                        current.addMangaArrayMutation(response.data)
                    })
                    .catch(err => {
                        console.log(err)
                    })
                    .finally(() => (current.isLoading = false))
            },
        }
    }
</script>

<style>

</style>
