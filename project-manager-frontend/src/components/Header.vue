<script lang="ts">
import axios from 'axios'

export default {
  data() {
    return {
      status: ''
    }
  },
  mounted() {
    axios.get('http://localhost:9090/api/status')
      .then(response => this.status = response.data)
  }
}
</script>

<template>
  <header class=" mx-auto w-screen bg-blue-200">
    <section class="container mx-auto p-0.5 h-auto rounded-md">
      <nav class="flex flex-row justify-between items-center text-xl font-bold font-bold uppercase">
        <div className="size-20">
          <img src="@/assets/logo.png" alt="" class="object-cover object-center size-full p-1" />
        </div>
        <span class="inline-block ">
          Project Tracking
        </span>
        <p>
          API status
          <span v-if="status?.status =='OK'" class="text-green-800 h-3 w-3 size-3 bg-green-500 rounded-full p-1">ok</span>
          <span v-else class="text-red-800   h-3 w-3 size-3 bg-red-500   rounded-full p-1">x</span>
        </p>
      </nav>
    </section>
  </header>
</template>

<style scoped>

</style>
