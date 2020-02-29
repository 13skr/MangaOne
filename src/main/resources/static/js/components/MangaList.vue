<template>
    <div>
        <div class="create">
            <form @submit.prevent="createManga">
                <input type="text" v-model="name" placeholder="Name">
                <input type="text" v-model="author" placeholder="Author">
                <button type="submit">Create</button>
            </form>
        </div>
        <div class="list">
            <ul v-if="mangas.length">
                <li class="item" v-for="manga in mangas" :manga="manga" :key="id">
                    {{ manga.name }}, {{manga.author}}
                    <button @click="deleteManga(manga.id)">
                        Remove
                    </button>
                </li>
            </ul>
            <p v-else>
                Nothing left! Please add new Manga.
            </p>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        data() {
            return {
                mangas: [],
                id: null,
                name: '',
                author: ''
            }
        },
        methods: {
            createManga() {
                axios.post("/manga", {
                    name: this.name,
                    author: this.author
                })
                    .then(() => this.getManga())
                    .catch(e => {
                        console.exception(e)
                    })
            },
            getManga() {
                axios.get('/manga')
                    .then(response => {
                        this.mangas = response.data
                    })
                    .catch(e => {
                        console.log(e)
                    })
            },
            deleteManga (idToRemove) {
                axios.delete("/manga/" + idToRemove)
                    .then(() => this.getManga())
                    .catch(e => console.exception(e))
            }
        },
        mounted() {
            this.getManga()
        }
    }
</script>

<style scoped>

    div {
        color: white;
    }

    p {
        text-align: center;
    }

    input {
        width: 100px;
        padding: 8px 10px;
        border: 1px solid;
    }

    .item, .create{
        display: flex;
        align-items: self-start;
        justify-content: center;
    }

</style>