<template>
  <div class="staff">
    <div class="staff-top">
      	<div class="search-box">
          <el-form
            :inline="true"
            ref="search_data"
            :rules="rules"
            :model="search_data"
          >
            <el-form-item label="关键词:">
              <el-input type="text" v-model="search_data.title" placeholder="请输入关键词"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" icon="search" @click='onScreeoutMoney("search_data")'>筛选</el-button>
            </el-form-item>
          </el-form>
        </div>
        <el-form :inline="true">
            <el-form-item class="btnRight">
                <el-button type="primary" size ="small" icon="el-icon-edit-outline" @click='onAddMoney()'>添加</el-button>
            </el-form-item>
        </el-form>
    </div>
      <div class="tables">
           <el-table
            :data="flowerData.list"
            border
            style="width: 100%">
            <el-table-column
                label="id"
                align="center"
                width="180">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.id }}</span>
                </template>
            </el-table-column>
            <el-table-column
                label="分类"
                align="center"
                width="180">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.categoryName }}</span>
                </template>
            </el-table-column>
            <el-table-column
                label="标题"
                align="center"
                width="180">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.title }}</span>
                </template>
            </el-table-column>
            <el-table-column
                label="图片"
                align="center"
                width="180">
                <template slot-scope="scope">
                    <img style="width:100px;height=80px;" alt="图片" :src="'api/'+scope.row.images"/>
                </template>
            </el-table-column>
            <el-table-column
                label="价格"
                align="center"
                width="180">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.price }}</span>
                </template>
            </el-table-column>
            <el-table-column
                label="花语"
                align="center"
                width="180">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.flowerLanguage }}</span>
                </template>
            </el-table-column>
            <el-table-column
                label="适宜人群"
                align="center"
                width="180">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.appropriateCrowd }}</span>
                </template>
            </el-table-column>
            <el-table-column
                label="更新时间"
                align="center"
                width="180">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.updateTime | moment }}</span>
                </template>
            </el-table-column>
            <el-table-column
                label="创建时间"
                align="center"
                width="180">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.createTime | moment }}</span>
                </template>
            </el-table-column>
            <el-table-column label="操作" fixed="right">
                <template slot-scope="scope">
                    <el-button
                    size="mini"
                    @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                </template>
            </el-table-column>
        </el-table>
      </div>
      <FlowerDialong :dialong="dialong" :form="form" :parameter="parameter" @flowerData="loadCurrentPageflowerList"></FlowerDialong>
      <div class="page">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="flowerData.pageNum"
          :page-sizes="flowerData.page_sizes"
          :page-size="flowerData.pageSize"
          :layout="flowerData.layout"
          :total="flowerData.total">
        </el-pagination>
	 	  </div>
  </div>
</template>

<script>
// @ is an alias to /src
import FlowerDialong from "../../components/FlowerDialong";
export default {
  name: "flowerList",
  data() {
    return {
      flowerData: {
        list: [],
        pageNum: 1,
        pageSize: 3,
        pages: 0,
        total: 0,
        page_sizes:[3,6,9,12], //每页显示多少条
				layout:'total, sizes, prev, pager, next, jumper'
      }, //数据存储
      dialong: {
        //弹出框
        show: false,
        title: "",
        option: "edit"
      },
      form: {   //添加和删除需要传递的字段名
        id: 0,
        cid: 0,
        title: 0,
        images: '',
        imagesList: '',
        price: '',
        flowerLanguage: '',
        appropriateCrowd: '',
        isDeleted: 0,
        createTime: '',
        updateTime: '',
      },
      parameter: {
        options: [{
                id: null,
                name: null
        }],
        id: 0,
        dialogImageUrls: []
      },
      search_data: {
         title: null
      }
    };
  },
  methods: {
    flowerList(formData) {
      this.$axios
        .post("/api/flower/list",formData,{headers: {"token": localStorage.getItem("eleToken")}})
        .then(res => {
          this.flowerData = res.data.data;
        }).catch(err => console.log(err));
    },categoryList() {
      this.$axios
        .get("/api/category",{headers: {"token": localStorage.getItem("eleToken")}})
        .then(res => {
          this.parameter.options = res.data.data;
        }).catch(err => console.log(err));
    },
     loadCurrentPageflowerList() {
	      let pageSize = this.flowerData.pageSize;
        let page = this.flowerData.pageNum;
				const formData = {
                "isAsc": null,
                "orderBy": null,
                "orderByColumn": null,
                "pageNum": page,
                "pageSize": pageSize
        }
        this.flowerList(formData);
    },
    handleEdit(index, row) {
      this.parameter.dialogImageUrls = [];
      //编辑
      this.dialong = {
        title: "编辑信息",
        show: true,
        option:"edit"
      }
      console.log(row);
      this.parameter.id = row.id;
      if(row.imagesList!=null) {
        let urlArr = row.imagesList.split(",");
        urlArr.forEach((item,value) => {
            if(!item) {
              return urlArr.splice(value,1);
            }
            if(typeof(item)=="undefined") {
                  return urlArr.splice(value,1);
            }
            this.parameter.dialogImageUrls.push({name:value,url:'api'+item})
        });
      }
      this.form = {
         id: row.id,
         cid: row.cid,
         title: row.title,
         images: row.images,
         imagesList: row.imagesList,
         price: row.price,
         flowerLanguage: row.flowerLanguage,
         appropriateCrowd: row.appropriateCrowd
      };
      this.categoryList();
    },
    onAddMoney() {
      this.parameter.dialogImageUrls = [];
      //添加内容
      this.dialong = {
        title: "添加信息",
        show: true,
        option:"add"
      }
       this.form = {
         cid: '',
         title: "",
         images: null
      };
      this.categoryList();
    },
    handleSizeChange(page_size) {
				this.flowerData.pageNum = 1; //第一页
				this.flowerData.pageSize = page_size; //每页先显示多少数据
		},
		handleCurrentChange(page){
				// 跳转页数
				//获取每行数
				let pageSize = this.flowerData.pageSize;
				const formData = {
                "isAsc": null,
                "orderBy": null,
                "orderByColumn": null,
                "pageNum": page,
                "pageSize": pageSize,
                "title": this.search_data.title
        }
        this.flowerList(formData);
		},
    onScreeoutMoney(){
			// if(this.search_data.title === '') {
			// 	 this.$message({
			// 		message: '关键词不能为空',
			// 		type: 'warning'
			// 	});
			// }
      const formData = {
                "isAsc": null,
                "orderBy": null,
                "orderByColumn": null,
                "pageNum": 1,
                "pageSize": this.flowerData.pageSize,
                "title": this.search_data.title
      }
      this.flowerList(formData);
		}
  },
  created() {
     const formData = {
                "isAsc": null,
                "orderBy": null,
                "orderByColumn": null,
                "pageNum": 1,
                "pageSize": this.flowerData.pageSize
    }
    this.flowerList(formData);
  },
  components: {
    FlowerDialong
  }
};
</script>
<style scoped>
.staff {
  margin: 10px;
}
.btnRight {
  float: right;
}
.search-box {
	margin: 20px 0 0 20px;
}
</style>
