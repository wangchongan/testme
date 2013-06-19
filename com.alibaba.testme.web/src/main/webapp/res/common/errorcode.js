function getErrorMessage(code) {
	switch(code) {
		case 0:
			return '数据工厂内部错误';
		case 1:
			return '未知的系统错误';
		case 2:
			return '未查询到任何满足条件且可被调用的TD事务';
		case 3:
			return 'TD事务状态异常';
		case 4:
			return 'TD事务内部发生业务异常';
		case 5:
			return '保存TD Workspace的jar文件失败';
		case 6:
			return 'TD事务调用外部系统时，外部系统发生错误';
		case 7:
			return 'TD Workspace部署失败';
		case 8:
			return '用户无权限进行当前操作';
		case 9:
			return 'TD事务发生未知错误';
		case 10:
			return '不被数据工厂支持的TD Workspace文件类型，目前仅支持jar类型';
		case 11:
			return '当前操作不被允许';
		case 12:
			return 'TD Workspace文件存储的路径非法';
		case 13:
			return '当前TD事务调用其他TD事务，被调用的TD事务发生错误';
		case 14:
			return '添加TD Workspace失败，该TD Workspace已存在';
		case 15:
			return '添加TD事务失败，该TD事务已存在';
		case 16:
			return '更新TD Workspace失败';
		case 17:
			return '上传的TD Workspace文件超过系统的限制，目前上限是50MB';
		case 18:
			return '该文件已损坏';
		case 19:
			return '类型转换失败。输入参数无法转换成TD事务所需入参类型';
	}
}
