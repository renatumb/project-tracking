<script lang="ts">

import axios from 'axios'
import { IoTrashOutline } from 'oh-vue-icons/icons'

export default {
  data() {
    return {
      clients: [],
      showModalAdd: false,

      formDataNew: {
        name: '',
        description: ''
      },

      selectedClient: {
        index: null,
        cliendId: null
      }
    }
  },
  mounted() {
    this.getData()
  },
  methods: {
    getData(page = 0, size = 50) {
      axios.get(`http://localhost:9090/api/client?page=${page}&size=${size}`)
        .then(response => {
          console.log(response.data.content)
          this.clients = response.data.content
        })
    },

    removeClient(cliendId) {
      axios.delete(`http://localhost:9090/api/client/${cliendId}`)
        .then(
          response => {
            alert('Deleted sucessffuly')
            this.getData()
          }
        ).catch(
        error => {
          let resp = JSON.parse(error.request.response)
          alert(error + '\n\n' + resp.message)
          console.log(error)
        }
      )
    },

    submitForm() {
      axios.post(`http://localhost:9090/api/client`, this.formDataNew)
        .then(
          response => {
            alert('Added successfully')
            this.getData()
            this.showModalAdd = false
          }
        ).catch(
        error => {
          let resp = JSON.parse(error.request.response)
          alert(error + '\n\n' + resp.message)
          console.log(error)
          this.showModalAdd = false
        }
      )

    },

    selectClient(index, client) {
      this.selectedClient.index = index
      this.selectedClient.client = client
      this.$emit('item-selected', this.selectedClient.client)
    }
  },

  emits: ['item-selected']
}
</script>


<template>
  <section class="bg-green-200 m-4 rounded-sm p-1">
    <div class="flex flex-row justify-between items-center p-2">
      <p class="uppercase font-bold">CLIENTS</p>
      <button class="bg-blue-400 rounded p-2 text-xs mx-1 hover:scale-105 transition-all font-semibold" @click="showModalAdd = !showModalAdd">ADD</button>
    </div>
    <ul>
      <li v-for="(item, index) in clients" :key="index" @click="selectClient(index, item)">
        <div class="flex flex-row justify-between items-center gap-2 m-3 p-2 bg-green-500 rounded-md hover:scale-105 transition-all  border " :class="{'border-red-700  ': ( index == selectedClient.index ) }">
          <div>
            <p class="font-semibold">{{ item.name }}</p>
            <p>{{ item.description }}</p>
          </div>
          <div>
            <button class="bg-red-400 rounded p-1 text-xs mx-1" @click="removeClient( item.id)">
              remove
            </button>
            <button class="bg-yellow-500 rounded p-1 text-xs mx-1" @click="modifyClient( item.id)">
              modify
            </button>
          </div>
        </div>
      </li>
    </ul>
  </section>

  <section class="border-2 bg-black fixed w-2/3 h-4/5 top-0 m-auto   opacity-90 backdrop-blur-3xl" v-if="showModalAdd">
    <button class="absolute top-2 right-4 font-extrabold text-red-500 text-3xl border border-red-500 rounded-full p-2 hover:scale-105 transition-all" @click="showModalAdd = !showModalAdd">X</button>
    <div class="flex flex-col justify-center  items-center gap-10 text-yellow-700">
      -
      <div>
        <span class="font-bold text-3xl">ADD NEW CLIENT</span>
      </div>
      <form @submit.prevent="submitForm" class="flex flex-col gap-3 p-6 border font-semibold">

        <label for="name">Name:</label>
        <input type="text" id="name" v-model="formDataNew.name" required>

        <label for="description">Description:</label>
        <textarea id="description" v-model="formDataNew.description" required></textarea>

        <button type="submit" class="bg-green-400 rounded p-2 text-xs font-bold hover:scale-105 transition-all">Submit</button>
      </form>
    </div>
  </section>
</template>

<style scoped>

</style>
